package homework.ojclass.end;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;


public class Main2 {
    static LinkedHashSet<String> outputResult = new LinkedHashSet<>();

    static String endId = "";

    static Map<String, Node> map=new HashMap<>();

    static void getLogOfModel(String modelLocation, String outputLocation){

        Document document = null;
        try{
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            document = builder.parse(new File(modelLocation));
            Element e = document.getDocumentElement();
        }catch (Exception e) {
            e.printStackTrace();
        }

        NodeList list = document.getElementsByTagName("place");
        for (int i = 0; i < list.getLength(); i++) {
            Element element = (Element) list.item(i);
            String id=element.getAttribute("id");
            String text=element.getElementsByTagName("text").item(0).getTextContent();
            map.put(id, new Node(id, text, true));
        }
        list = document.getElementsByTagName("transition");
        for (int i = 0; i < list.getLength(); i++) {
            Element element = (Element) list.item(i);
            String id=element.getAttribute("id");
            String text=element.getElementsByTagName("text").item(0).getTextContent();
            map.put(id, new Node(id, text, false));
        }


        list = document.getElementsByTagName("arc");
        for (int i = 0; i < list.getLength(); i++) {
            Element element = (Element) list.item(i);
            String source=element.getAttribute("source");
            String target=element.getAttribute("target");
            if(map.containsKey(source)){
                map.get(source).addNext(map.get(target));
                map.get(target).addPre(map.get(source));
            }
        }

        Node beginNode = getBeginNode();
        Node endNode = getEndNode();

        if(beginNode.nextSize()!=0){
            List nodeP=new ArrayList();
            nodeP.add(beginNode.getId());
            List<Node> nodeList=new ArrayList<>();
            nodeList.add(beginNode);
            search(null, nodeList, new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        }

        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(outputLocation);
            int i=0;
            for(String s:outputResult){
                System.out.println(s);
                fileWriter.write(s+"\r\n");
            }
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Node getBeginNode(){
        Node begin = null;
        for(Map.Entry<String, Node> entry : map.entrySet()){
            Node temp = entry.getValue();
            if(temp.getType() && temp.preSize()==0){
                begin = temp;
                break;
            }
        }
        return begin;
    }

    private static Node getEndNode(){
        Node end = null;
        for(Map.Entry<String, Node> entry : map.entrySet()){
            Node temp = entry.getValue();
            if(temp.getType()&&temp.nextSize()==0){
                end=temp;
                endId=end.getId();
                break;
            }
        }
        return end;
    }

    private static void search(Node n,List<Node> ps,List<String> path, List<Node> NodeList, List<String> noteId, List<String> toFinishId) {
        if(null!=n) {//transition
            List<String> pstr=new ArrayList<>(path);
            List<String> nidstr=new ArrayList<>(noteId);
            nidstr.add(n.getId());
            pstr.add(n.getText());
            List<Node> next=new ArrayList<>();
            for(Node ne:n.getNext()){
                if(!nidstr.contains(ne.getId())||
                        nidstr.indexOf(ne.getId())==nidstr.lastIndexOf(ne.getId())) {
                    next.add(ne);
                }
            }
            search(null,next,pstr,NodeList,nidstr,toFinishId);
        }else if(ps.size()!=0){//place
            boolean fin=false;
            for(Node p:ps){
                if(p.getId().equals(endId)){
                    StringBuilder stringBuilder = new StringBuilder();
                    for (int i = 0; i < path.size(); i++) {
                        if (i == path.size() - 1) {
                            stringBuilder.append(path.get(i));
                        } else {
                            stringBuilder.append(path.get(i));
                            stringBuilder.append("--");
                        }
                    }
                    outputResult.add(stringBuilder.toString());
                    fin=true;
                    break;
                }
                toFinishId.add(p.getId());
                noteId.add(p.getId());
            }
            if(!fin){
                List<List<Node>> nexts = getAllList(ps);
                for(List<Node> next:nexts){
                    List<Node> tmp=new ArrayList<>(NodeList);
                    tmp.addAll(next);
                    for(int i=0;i<tmp.size();){
                        Node node=tmp.get(i);
                        if(!noteId.contains(node.getId())||
                                noteId.indexOf(node.getId())==noteId.lastIndexOf(node.getId())){
                            boolean isFinished=true;
                            List<String> tf=new ArrayList<>(toFinishId);
                            for(Node nn:node.getPre()){
                                if(tf.contains(nn.getId())){
                                    tf.remove(tf.indexOf(nn.getId()));
                                }else{
                                    isFinished=false;
                                    break;
                                }
                            }

                            if(isFinished){//可执行
                                List<Node> nl=new ArrayList<>(tmp);
                                nl.remove(i);
                                List<String> nid=new ArrayList<>(noteId);

                                search(node,null,path,nl,nid,tf);
                            }
                            i++;

                        }else{
                            tmp.remove(i);
                        }
                    }
                }

            }

        }

    }

    private static List<List<Node>> getAllList(List<Node> nodeList){
        List<List<Node>> lists= new ArrayList<>();
        getList(nodeList,new ArrayList<>(),lists,0);
        return lists;
    }

    private static void getList(List<Node> nodeList,List<Node> toAdd,List<List<Node>> res,int index){
        if(nodeList!=null){
            if(index==nodeList.size()){
                res.add(toAdd);
            }else{
                List<Node> next = nodeList.get(index).getNext();
                for(int i=0;i<next.size();i++){
                    List<Node> ad=new ArrayList<>(toAdd);
                    ad.add(next.get(i));
                    getList(nodeList,ad,res,index+1);
                }
            }
        }
    }

    public static void main(String[] args){
        getLogOfModel("Model2.pnml","out1.txt");
    }
}


class Node {
    private String id;
    private String text;
    private List<Node> pre;
    private List<Node> next;
    private boolean type;//place true,transition false;

    public Node(String id, String text, boolean type){
        this.type=type;
        this.id=id;
        this.pre=new ArrayList<>();
        this.next=new ArrayList<>();
        this.text=text;
    }

    public boolean getType() {
        return type;
    }

    public int preSize(){
        return this.pre.size();
    }

    public int nextSize(){
        return this.next.size();
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Node> getPre() {
        return pre;
    }

    public void addPre(Node pre) {
        this.pre.add(pre);
    }

    public List<Node> getNext() {
        return next;
    }

    public void addNext(Node next) {
        this.next.add(next);
    }
}



