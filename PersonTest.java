class Person {

   // Properties of the class...
   private String name;
   public int    age;
   private int    heartrate;
    
   // Constructor of the class...
   //public Person(String aName, int anAge, int aHeartrate) {
   //   name = aName;
   //   age  = anAge;
   //   heartrate = aHeartrate;
   //}

   // Methods of the class...
   public void talk() {
      System.out.println("Hi, my name's " + name);
      System.out.println("and my age is " + age);
      if (heartrate != 0) {
         System.out.println("It's alive");
      }
      else{
         System.out.println("It's dead");
      }
      commentAboutAge();
      System.out.println();

   }
   public void commentAboutAge() {
      if (age < 5) {
         System.out.println("baby");
      }
      if (age == 5) {
         System.out.println("time to start school");
      }
      if (age > 59) {
         System.out.println("old person");
      }
   }
   public void growOlder() {
      age += 1;
   }
   public void giveKnighthood(){
      name = "Sir " + name;
   }
   public void growOlderBy(int years){
      age += years;
   }

}

class PersonTest {

   // The main method is the point of entry into the program...
   public static void main(String[] args) {

      Person ls = new Person();
      Person wp = new Person();
      System.out.println(ls.age);
      ls.talk();
      ls.growOlderBy(10);
      wp.giveKnighthood();
      wp.growOlder();
      wp.talk();
   }

}

