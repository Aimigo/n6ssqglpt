package www.quality.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
 class Entity 
{
private String id;
private String groupName;
private String pid;
private boolean checkGroup;
private List<Entity> children;
 
public Entity(String id, String groupName, String pid, boolean checkGroup) {
super();
this.id = id;
this.groupName = groupName;
this.pid = pid;
this.checkGroup = checkGroup;
}
 
public String getId() {
return id;
}
 
public void setId(String id) {
this.id = id;
}
 
public String getGroupName() {
return groupName;
}
 
public void setGroupName(String groupName) {
this.groupName = groupName;
}
 
public String getPid() {
return pid;
}
 
public void setPid(String pid) {
this.pid = pid;
}
 
public boolean isCheckGroup() {
return checkGroup;
}
 
public void setCheckGroup(boolean checkGroup) {
this.checkGroup = checkGroup;
}
 
public List<Entity> getChildren() {
return children;
}
 
public void setChildren(List<Entity> children) {
this.children = children;
}
}


public class EneityUtil {
public static List<Entity> getResult(List<Entity> src)
{
List<Entity> parents = new ArrayList<Entity>();
for(Entity ent : src)
{
if(ent.isCheckGroup())
{
Entity result = ent;
result.setChildren(new ArrayList<Entity>());
parents.add(result);
}
}
List<Entity> last = new ArrayList<Entity>();
for(Entity ent : src)
{
if(!ent.isCheckGroup())
{
last.add(ent);
}
}
buildTree(parents, last);
return parents;
}
private static void buildTree(List<Entity> parents, List<Entity> others)
{
List<Entity> record = new ArrayList<Entity>();
for(Iterator<Entity> it = parents.iterator(); it.hasNext();)
{
Entity vi = it.next();
if(vi.getId() != null)
{
for(Iterator<Entity> otherIt = others.iterator(); otherIt.hasNext();)
{
Entity inVi = otherIt.next();
if(vi.getId().equals(inVi.getPid()))
{
if(null == vi.getChildren())
{
vi.setChildren(new ArrayList<Entity>());
}
vi.getChildren().add(inVi);
record.add(inVi);
otherIt.remove();
}
}
}
}
if(others.size() == 0)
{
return;
}
else
{
buildTree(record, others);
}
}
@SuppressWarnings("all")
public static void main(String[] args) {
List<Entity> src = new ArrayList<Entity>();
Entity ent0 = new Entity("0", "ALL", null, true);
Entity ent1 = new Entity("1", "中国", "0" , false);
Entity ent2 = new Entity("11", "YY", "1" , false);
Entity ent3 = new Entity("111", "XX", "11", false);
Entity ent6 = new Entity("1111", "ZZ", "111", false);
Entity ent4 = new Entity("2", "美国", "0" , false);
Entity ent5 = new Entity("21", "华盛顿", "2" , false);
src.add(ent0);
src.add(ent1);
src.add(ent2);
src.add(ent3);
src.add(ent4);
src.add(ent5);
src.add(ent6);
List<Entity> result = getResult(src);
System.out.println(result.size());
}
 
}