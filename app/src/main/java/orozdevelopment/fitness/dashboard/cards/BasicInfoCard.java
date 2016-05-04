package orozdevelopment.fitness.dashboard.cards;

/**
 * Created by michael on 5/4/16.
 */
public class BasicInfoCard extends ICard{
    String name;
    int personalPhotoId;
    int age;
    int weight;
    int height;
    double bmi;


    public BasicInfoCard(String name, int age, int weight, int height, double bmi, int photoId){
        super(0);
        this.name = name;
        this.age = age;
        this.weight = weight;
        this.height = height;
        this.bmi = bmi;
        this.personalPhotoId = photoId;
    }
}
