package runners;

public class Generator {
  public static void main(String[] args) {
    var generator = new mg.razherana.generator.Generator(
        "src/main/webapp/WEB-INF/generator.xml");
    generator.generateFiles();
  }
}