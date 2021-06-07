package grocery.base.pojo;

public class Brand {
    int brandId;
    String brandName;
    int parentId;

    public Brand() {}

    public Brand(int brandId, String brandName, int parentId) {
        this.brandId = brandId;
        this.brandName = brandName;
        this.parentId = parentId;
    }

    public int getBrandId() {
        return brandId;
    }

    public void setBrandId(int brandId) {
        this.brandId = brandId;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }
}