package pe.egcc.edutec.dto;

public class ComboDTO {

  private String code;
  private String name;
  private int selected;

  public ComboDTO() {
  }

  public ComboDTO(String code, String name) {
    this(code, name, 0);
  }

  public ComboDTO(String code, String name, int selected) {
    this.code = code;
    this.name = name;
    this.selected = selected;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getSelected() {
    return selected;
  }

  public void setSelected(int selected) {
    this.selected = selected;
  }

}
