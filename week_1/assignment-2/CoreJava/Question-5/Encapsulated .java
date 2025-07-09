class StudentData {
    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}

public class Encapsulated {
    public static void main(String[] args) {
        StudentData s = new StudentData();
        s.setName("Ashish");
        s.setAge(22);

        System.out.println(s.getName());
        System.out.println(s.getAge());
    }
}