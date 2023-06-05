package mvcStudent;

public class Student {
    private int id;
    private String name;
    private String grade;
    private double scores;

    public Student(int id ,String name, String grade, double scores) {
        super();
        this.id = id;
        this.name = name;
        this.grade = grade;
        this.scores = scores;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public double getScores() {
        return scores;
    }

    public void setScores(double scores) {
        this.scores = scores;
    }
}
