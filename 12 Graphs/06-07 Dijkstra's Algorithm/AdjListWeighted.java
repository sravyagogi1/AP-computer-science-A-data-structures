// Name: Sravya Gogineni
// Date: 5/28/25
 
import java.util.*;
import java.io.*;

/* Resource classes and interfaces for 
 *              Graphs6: Dijkstra
 *              Graphs7: Dijkstra with Cities
 */

class Neighbor implements Comparable<Neighbor>
{
   //2 Neighbors are equal if and only if they have the same name
   //implement all methods needed for a HashSet and TreeSet to work with Neighbor objects
   private final wVertex target;
   private final double edgeDistance;
   
   public Neighbor(wVertex t, double d) {
      target = t;
      edgeDistance = d;
   }
   
   //add all methods needed for a HashSet and TreeSet to function with Neighbor objects
   //use only target, not distances, since a vertex can't have 2 neighbors that have the same target
   //.........
   public wVertex getTarget(){
      return target;
   }
   
   public double getDistance(){
      return edgeDistance;
   }
   
   public int compareTo(Neighbor other) {
      return this.target.getName().compareTo(other.target.getName());
   }
   
   public String toString()
   {
      return target.getName() + " " + edgeDistance;  
   }
}

 /**************************************************************/
class PQelement implements Comparable<PQelement> { 
//used just for a PQ, contains a wVertex and a distance, also previous that is used for Dijksra 7
//compareTo is using the distanceToVertex to order them such that the PriorityQueue works
//will be used by the priority queue to order by distance
 
   private wVertex vertex;
   private Double distanceToVertex; 
   private wVertex previous; //for Dijkstra 7
      
   public PQelement(wVertex v, double d) {
      vertex = v;
      distanceToVertex = d;
   }
   
   //getter and setter methods provided
   public wVertex getVertex() {
      return this.vertex;
   }
   
   public Double getDistanceToVertex() {
      return this.distanceToVertex;
   }
   
   public void setVertex(wVertex v) {
      this.vertex = v;
   }
   
   public void setDistanceToVertex(Double d) {
      this.distanceToVertex = d;
   }   
   
   public int compareTo(PQelement other) {
      //we assume no overflow will happen since distances will not go over the range of int
      return (int)(distanceToVertex - other.distanceToVertex);
   }
   
   public wVertex getPrevious()  //Dijkstra 7
   {
      return this.previous;
   }
   public void setPrevious(wVertex v) //Dijkstra 7
   {
      this.previous = v;
   } 
   
   //implement toString to match the sample output    ______________
   public String toString()
   { 
      String toReturn = "";
      //your code here...
      toReturn += vertex.getName() + " " + distanceToVertex;
      return toReturn;
   }
}

/********************* wVertexInterface ************************/
interface wVertexInterface 
{
   public String getName();
   
   public Set<Neighbor> getNeighbors();
   
   /*  adds to the neighbors set  
       called at the beginning of the lab*/
   public void addAdjacent(wVertex v, double d); 
     
    /*returns an arraylist of PQelements that store distanceToVertex to another wVertex  */
   public ArrayList<PQelement> getAlDistanceToVertex();
   
   //returns the PQelement that has the vertex equal to v
   public PQelement getPQelement(wVertex v);
   
   /*
   postcondition: returns null if wVertex v is not in the alDistanceToVertex
                  or the distance associated with that wVertex in case there is a PQelement that has v as wVertex
   */
   public Double getDistanceToVertex(wVertex v);
   
   /*
   precondition:  v is not null
   postcondition: - if the alDistanceToVertex has a PQelement that has the wVertex component equal to v
                  it updates the distanceToVertex component to d
                  - if the alDistanceToVertex has no PQelement that has the wVertex component equal to v,
                  then a new PQelement is added to the alDistanceToVertex using v and d   
   */
   public void setDistanceToVertex(wVertex v, double d); 
 
   public String toString();  
 
}

class wVertex implements Comparable<wVertex>, wVertexInterface 
{ 
   public static final double NODISTANCE = Double.POSITIVE_INFINITY; //constant to be used in class
   private final String name;
   private Set<Neighbor> neighbors;  
   private ArrayList<PQelement> alDistanceToVertex; //should have no duplicates, enforced by the getter/setter methods
  
   /* constructor, accessors, modifiers  */ 
   public wVertex(String name){
      this.name = name;
      this.neighbors = new HashSet<>();
      this.alDistanceToVertex = new ArrayList<>();
   }
   
   /* 2 vertexes are equal if and only if they have the same name
      add all methods needed for a HashSet and TreeSet to function with Neighbor objects
      use only target, not distances, since a vertex can't have 2 neighbors that have the same target   
   */
   
   public int compareTo(wVertex other) {
    return this.name.compareTo(other.name);
}
   
   public String getName(){
      return name;
   }
   
   public Set<Neighbor> getNeighbors(){
      return neighbors;
   }
   
   /*  adds to the neighbors set  
       called at the beginning of the lab*/
   public void addAdjacent(wVertex v, double d){
      neighbors.add(new Neighbor(v,d));
   }
     
    /*returns an arraylist of PQelements that store distanceToVertex to another wVertex  */
   public ArrayList<PQelement> getAlDistanceToVertex(){
      return alDistanceToVertex;
   }
   
   //returns the PQelement that has the vertex equal to v
   public PQelement getPQelement(wVertex v){
      for(PQelement p: alDistanceToVertex) {
         if(p.getVertex().equals(v))
            return p;
      }
      return null;
   }
   
   /*
   postcondition: returns null if wVertex v is not in the alDistanceToVertex
                  or the distance associated with that wVertex in case there is a PQelement that has v as wVertex
   */
   public Double getDistanceToVertex(wVertex v){
      for(PQelement p: alDistanceToVertex) {
         if(p.getVertex().equals(v))
            return p.getDistanceToVertex();
      }
      return null;
   }
   
   /*
   precondition:  v is not null
   postcondition: - if the alDistanceToVertex has a PQelement that has the wVertex component equal to v
                  it updates the distanceToVertex component to d
                  - if the alDistanceToVertex has no PQelement that has the wVertex component equal to v,
                  then a new PQelement is added to the alDistanceToVertex using v and d   
   */
   public void setDistanceToVertex(wVertex v, double d){
      PQelement x = getPQelement(v);
      if(x!=null)
         x.setDistanceToVertex(d);
      else
         alDistanceToVertex.add(new PQelement(v,d));
   } 
   
   public String toString()
   { 
      String toReturn = name;
      toReturn += " "+ neighbors;
      toReturn += " List: " + alDistanceToVertex; 
      return toReturn;
   }
}

/*********************   Interface for Graphs 6:  Dijkstra ****************/
interface AdjListWeightedInterface 
{
   public Set<wVertex> getVertices();  
   public Map<String, wVertex> getVertexMap();  //this is just for codepost testing
   public wVertex getVertex(String vName);
   /* 
      postcondition: if a Vertex with the name v exists, then the map is unchanged.
                     addVertex should work in O(logn)
   */
   public void addVertex(String vName);
   /*
      precondition:  both Vertexes, source and target, are already stored in the graph.
                     addEdge should work in O(1)
   */   
   public void addEdge(String source, String target, double d);
   public void minimumWeightPath(String vertexName); // Dijstra's algorithm
   public String toString();  
}  

 /***********************  Interface for Graphs 7:  Dijkstra with Cities   */
interface AdjListWeightedInterfaceWithCities 
{       
   public List<String> getShortestPathTo(wVertex vSource, wVertex target);
   public void readData(String vertexNames, String edgeListData) ;
}
 
/****************************************************************/ 
/**************** this is the graph  ****************************/
public class AdjListWeighted implements AdjListWeightedInterface//,AdjListWeightedInterfaceWithCities
{
   //we want our map to be ordered alphabetically by vertex name
   private Map<String, wVertex> vertexMap = new TreeMap<String, wVertex>();
   
   /* default constructor -- not needed!  */
  
   /* similar to AdjList, but handles distances (weights) and wVertex*/ 
   
   public Set<wVertex> getVertices(){
      Set<wVertex> s = new HashSet<>();
      for(String x:vertexMap.keySet())
         s.add(vertexMap.get(x));
      return s;
   }
     
   public Map<String, wVertex> getVertexMap(){  //this is just for codepost testing
      return vertexMap;
   }
   
   public wVertex getVertex(String vName){
      return vertexMap.get(vName);
   }
   /* 
      postcondition: if a Vertex with the name v exists, then the map is unchanged.
                     addVertex should work in O(logn)
   */
   public void addVertex(String vName){
      if(vertexMap.get(vName)==null)
         vertexMap.put(vName, new wVertex(vName));
   }
   /*
      precondition:  both Vertexes, source and target, are already stored in the graph.
                     addEdge should work in O(1)
   */   
   public void addEdge(String source, String target, double d){
      vertexMap.get(source).addAdjacent(vertexMap.get(target),d);
   }
   
   public void minimumWeightPath(String vertexName){ // Dijstra's algorithm
      
      wVertex source = getVertex(vertexName);
      for(wVertex v: getVertices())
         for(wVertex y: getVertices()) {
        v.setDistanceToVertex(y, Double.POSITIVE_INFINITY);
      }
      source.setDistanceToVertex(source,0);
      //add the source vertex, as its own PQelement, to the PQ
      PriorityQueue<PQelement> pq = new PriorityQueue<>();
      pq.add(new PQelement(source,0));
      
      while(!pq.isEmpty()){
         PQelement x = pq.remove();
         
         for(Neighbor n: x.getVertex().getNeighbors()){
            //calculate the new distance back to the source
            wVertex w = n.getTarget();
            double new_distance = source.getDistanceToVertex(x.getVertex()) + n.getDistance();
            double old_distance = source.getDistanceToVertex(w);
            
            if(new_distance < old_distance){
               //update PQelement's distance back to the source
               //place the updated PQelement back in the PQ
               source.setDistanceToVertex(w,new_distance);
               pq.add(new PQelement(w,new_distance));
            }
         }
       }
   }
   
   public String toString()
   {
      String strResult = "";
      for(String vName: vertexMap.keySet()){
         strResult += vertexMap.get(vName) + "\n"; 
      }
      return strResult;
   }
   
   /*  Graphs 7 has two more methods */
   public List<String> getShortestPathTo(wVertex target){
      List<String> l = new ArrayList<>();
      if(target==null)
         return l;
         
      wVertex c = target;
      while(c != null){
         l.add(c.getName());
         PQelement p = getVertexMap().get(l.get(l.size()-1)).getPQelement(c);
         if(p!= null)
            c = p.getPrevious();
      }
      return l;
   }  
     
   public void readData(String vertexNames, String edgeListData) 
   {
     /* use a try-catch  */
     Scanner a = null;
     try{ 
         a = new Scanner(new File(edgeListData)); 
         for(int i=0 ; i<Integer.parseInt(a.nextLine()); i++)
            addVertex(a.nextLine());
     }
      
     catch (IOException e){
        System.exit(0);
     }
         
     try{  
        a = new Scanner(new File(vertexNames)); 
        while(a.hasNext())
           addEdge(a.next(),a.next(),Double.parseDouble(a.next()));
     }
     
     catch(IOException e){
        System.exit(0);
     }

   }
}