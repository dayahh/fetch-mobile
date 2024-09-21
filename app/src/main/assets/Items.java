public class Items {

    int listid;
    String name;
    String id;

    public Items(int listid, String name, String id){
        this.listid = listid;
        this.id = id;
        this.name = name;
    }

    public int getListid() {
        return listid;
    }

    public void setListid(int listid) {
        this.listid = listid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    int listid;
    String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}