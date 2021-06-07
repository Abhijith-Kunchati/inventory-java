package grocery.base.pojo;

public class ParentBrand {
    int parentId;
    String parentName;

    public ParentBrand() {}

    public ParentBrand(int parentId, String parentName) {
        this.parentId = parentId;
        this.parentName = parentName;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }
}