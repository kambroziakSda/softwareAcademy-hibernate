package http.rest.academy.entity;

public class Academy {

    private String name;

    public Academy(String name) {
        this.name = name;
    }

    public Academy() {
    }


    @Override
    public String toString() {
        return "Academy{" +
                "name='" + name + '\'' +
                '}';
    }


    public String getName() {
        return name;
    }

}
