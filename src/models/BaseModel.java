package models;

public class BaseModel {
    private long id;
    private long currId = 0;

    public BaseModel(){
        this.id = currId;
        currId++;
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
}
