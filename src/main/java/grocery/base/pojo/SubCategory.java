package grocery.base.pojo;

public class SubCategory {
    int subCatId;
    String subCatName;
    int categoryId;
    String unit;

    public SubCategory() {}

    public SubCategory(int subCatId, String subCatName, int categoryId, String unit) {
        this.subCatId = subCatId;
        this.subCatName = subCatName;
        this.categoryId = categoryId;
        this.unit = unit;
    }

    public int getSubCatId() {
        return subCatId;
    }

    public void setSubCatId(int subCatId) {
        this.subCatId = subCatId;
    }

    public String getSubCatName() {
        return subCatName;
    }

    public void setSubCatName(String subCatName) {
        this.subCatName = subCatName;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}