//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: Fountain in which when the screen is clicked, a fountain appears there.
// Files: NONE
// Course: COMP SCI 300, Spring Semester, 2019
//
// Author: Maxwell Johnson
// Email: mkjohnson9@wisc.edu
// Lecturer's Name: GARY DAHL
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name: NONE
// Partner Email: NONE
// Partner Lecturer's Name: NONE
//
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
// __X_ Write-up states that pair programming is allowed for this assignment.
// ___ We have both read and understand the course Pair Programming Policy.
// ___ We have registered our team prior to the team registration deadline.
//
///////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
//
// Students who get help from sources other than their partner must fully
// acknowledge and credit those sources of help here. Instructors and TAs do
// not need to be credited here, but tutors, friends, relatives, room mates,
// strangers, and others do. If you received no outside help from either type
// of source, then please explicitly indicate NONE.
//
// Persons: NONE
// Online Sources: NONE
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////
import java.util.Random;

public class Fountain {
  private static Random randGen = new Random(); // private field randGen of the Random class
  private static Particle[] particles; // array of particles
  private static int positionX; // middle of the screen (left to right): 400
  private static int positionY; // middle of the screen (top to bottom): 300
  private static int startColor; // blue: Utility.color(23,141,235)
  private static int endColor; // lighter blue: Utility.color(23,200,255)



  /**
   * main method that executes the program
   * 
   * @param args
   */
  public static void main(String[] args) {
    Utility.runApplication(); // runs the utility application window
  }

  /**
   * setup method for the Utility application
   * 
   */
  public static void setup() {
    // tests a few methods
    if (!testUpdateParticle()) {
      System.out.println("updateParticle(): FAILED TEST");
    }
    if (!testRemoveOldParticles()) {
      System.out.println("removeOldParticles(): FAILED TEST");
    }

    positionX = 400; // middle of the screen (left to right)
    positionY = 300; // middle of the screen (top to bottom)
    startColor = Utility.color(23, 141, 235);// blue
    endColor = Utility.color(23, 200, 255);// lighter blue


    particles = new Particle[800];// creates a new particle array

  }

  /**
   * update method being repeatedly called necessary for the utility application
   * 
   */
  public static void update() {
    Fountain.createNewParticles(10);// creates 10 new particles at a time
    int colorBack = Utility.color(235, 213, 186);// color used as background
    Utility.background(colorBack); // passes a specific color into the background of the program
    updateParticle(10);// updates the particles positionto make it move
    removeOldParticles(80);// removes all particles over the age of 80

  }

  /**
   * helper method that updates the particles position, color, etc;
   * 
   * @param int index
   */
  private static void updateParticle(int index) {
    for (int i = 0; i <= index || i < particles.length; i++) {
      if (particles[i] != null) {
        float partSize = particles[i].getSize();
        float posX = particles[i].getPositionX();
        float posY = particles[i].getPositionY();
        float velX = particles[i].getVelocityX();
        float velY = particles[i].getVelocityY();// initializes variables for position and velocity
                                                 // from previous particle
        int age = particles[i].getAge();// gets the age of the particle
        Utility.fill(particles[i].getColor(), particles[i].getTransparency());


        particles[i].setVelocityY(velY + .3f);// adds a gravitational like affect to the particle
        velY = particles[i].getVelocityY();// gets the updated velocity
        particles[i].setPositionX(posX + velX);// sets position of x with updated value
        particles[i].setPositionY(posY + velY);// sets position of x with updated value
        particles[i].setAge(age + 1);
        Utility.circle(posX, posY, partSize);// draws a circle according to the particles position
      }
    }
  }

  /**
   * helper method that adds particles to the particle array
   * 
   * @param int num the number of particles that is to be created
   */
  private static void createNewParticles(int num) {
    // variables key to the creation of a particle
    float posX; // x position
    float posY; // y position
    float partSize; // size
    float velX; // x velocity
    float velY; // y velocity
    int age; // age of particle
    int color;// color of particle
    int transparency;// transparency of the particle
    int counter = 0;// counts how many null places have been taken up



    // loop creating new particles
    for (int i = 0; counter < num && i < particles.length; i++) // loops through the array of
                                                                // particles
    {
      // randomizing factors applied to the particles key variables
      posX = randGen.nextFloat() * 6 + (positionX - 3);// inclusive bounds of 3 integers within the
                                                       // positionX
      posY = randGen.nextFloat() * 6 + (positionY - 3);// inclusive bounds of 3 integers within the
                                                       // positionY
      partSize = randGen.nextFloat() * (7) + 4;// random float bounded by numbers 4 and 11
      color = Utility.lerpColor(startColor, endColor, randGen.nextFloat());// mixes the startColor
                                                                           // and endColor a random
                                                                           // amount
      velX = randGen.nextFloat() * 2 - 1;// random float b/t -1 and 1
      velY = randGen.nextFloat() * 5 - 10;// random float b/t -10 and -5
      age = randGen.nextInt(41);// random int between 0 and 40
      transparency = randGen.nextInt(95) + 32;// random int between 32 and 127

      // checks if the particle at a certain index is null
      if (particles[i] == null) {
        particles[i] = new Particle(posX, posY, partSize, color); // creates a randomized new
                                                                  // particle at every null index
        particles[i].setAge(age);// sets age
        particles[i].setVelocityX(velX);// sets x velocity
        particles[i].setVelocityY(velY);// sets y velocity
        particles[i].setTransparency(transparency);// sets transparency
        counter++;// counter increases every time a null instance is found
      }
    }



  }

  /**
   * method that removes old particles from the particles array
   * 
   * @param int maxAge maximum age a particle can be
   */
  private static void removeOldParticles(int maxAge) {
    int age; // age of the particle
    for (int i = 0; i < particles.length; i++) {
      if (particles[i] != null) {
        age = particles[i].getAge(); // gets the age of a particle
        if (age > maxAge) {
          particles[i] = null;// if the particles age is greater than maxAge, the particle is set to
                              // null(removed)
        }
      }
    }
  }

  /**
   * locates position of the mouse when it is clicked
   * 
   * @param int x x position of the mouse
   * @param int y y position of the mouse
   */
  private static void mousePressed(int x, int y) {
    Fountain.positionX = x;
    Fountain.positionY = y;// makes the fountain position of x and y to the coordinates of where the
                           // mouse is clicked
  }

  /**
   * whenever p is pressed, a screenshot is taken
   * 
   * @param char input the input character of the key pressed
   */
  private static void keyPressed(char input) {
    if (input == 'p' || input == 'P') {
      Utility.save("screenshot.png");// saves the screen when p is pressed on the keyboard
    }
  }

  /**
   * Creates a single particle at position (3,3) with velocity (-1,-2). Then checks whether calling
   * updateParticle() on this particle's index correctly results in changing its position to
   * (2,1.3).
   * 
   * @return true when no defect is found, and false otherwise
   */
  private static boolean testUpdateParticle() {

    particles = new Particle[1];
    particles[0] = new Particle();
    particles[0].setPositionX(3);
    particles[0].setPositionY(3);
    particles[0].setVelocityX(-1);
    particles[0].setVelocityY(-2);// sets the particles properties necessary to test
                                  // updateParticle()
    updateParticle(0);// calls updateParticle on the particle

    if (particles[0].getPositionX() == 2f && particles[0].getPositionY() == 1.3f) {
      return true;
    }
    return false; // TODO: implement this test
  }

  /**
   * Calls removeOldParticles(6) on an array with three particles (two of which have ages over six
   * and another that does not). Then checks whether the old particles were removed and the young
   * particle was left alone.
   * 
   * @return true when no defect is found, and false otherwise
   */
  private static boolean testRemoveOldParticles() {
    particles = new Particle[3];
    particles[0] = new Particle();
    particles[1] = new Particle();
    particles[2] = new Particle();
    particles[0].setAge(7);// creates new particle with age 7
    particles[1].setAge(7);// creates new particles with age 7
    particles[2].setAge(2);// creates new particle with age 2

    removeOldParticles(6);// removes particles in the array with a maximum age of 6
    if (particles[0] == null && particles[1] == null && particles[2] != null)// tests if the first
                                                                             // two were removed and
                                                                             // the last one wasnt
    {
      return true;
    }

    return false; // TODO: implement this test
  }
}
