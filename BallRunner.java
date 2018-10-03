
/**
 * @Veronica Pratt
 * @914
 */
public class BallRunner
{
    public static void ballRunner(){ //This is for activity One
        BallWorld ballWorld = new BallWorld(200, 200); //This is me creating all the variables activity one asked for
        TGPoint startPoint = new TGPoint(0.0, 0.0);
        BallBot ballBot = new BallBot(ballWorld, startPoint, 37, 25); 
        BallRunner ballObject = new BallRunner ();
        ballObject.activity1(ballWorld, startPoint, ballBot);
    }

    public static void activity1(BallWorld ballWorld, TGPoint startPoint, BallBot ballBot){ //Now this is the stuff to make my ball Bot move

        while(true){
            if (ballBot.canMoveForward(ballWorld)){ //Testing whether it can move forward
                ballBot.moveForward(); //Moving it forward if it can
            }else{ 
                ballBot.setHeading(ballBot.getHeading()%360+90); //This is resetting the direction of the ball bot so it can go forward 
            }
        }
    }

    public int findFreeBallBotIndex(BallBot[] ballBotArray){ //This is testing to see where there is a null value in the Ball Bot Array
        int freeBallBotIndex = ballBotArray.length;
        for(int i=0; i<ballBotArray.length; i++){
            if(ballBotArray [i] == null){
                freeBallBotIndex = i; 
            } 
        }
        return freeBallBotIndex; 
    }

    public  double distanceBetweenPoints(TGPoint point1, TGPoint point2){ //This is for activity 3
        double diffx = point1.x - point2.x; //this is testing how far away two things are
        double diffy = point1.y - point2.y;
        return Math.sqrt(diffx*diffx + diffy*diffy);
    }

    public  boolean entranceClear(BallBot[] ballBotArray, TGPoint entrancePoint){ //This is also for activity 3
        for(int i=0; i<ballBotArray.length; i++){
            if(ballBotArray [i] != null){ //This is making sure there's a ball Bot in the array to test
                if(distanceBetweenPoints(entrancePoint, ballBotArray[i].getPoint() ) - 2*(ballBotArray[i].getRadius()) <0){
                    return false;  //This is saying that there isn't enough room for a new Ball Bot
                }
            } 
        }
        return true; //this is saying there is room for a ball Bot
    }

    public BallBot ballBotToBounceOff(BallBot ballBot, BallBot [] ballBotArray){ //This is for activity 4
        TGPoint firstPoint = ballBot.getPoint(); //This is where BallBot is now
        TGPoint nextPoint = ballBot.forwardPoint();//This is where it will go
        for(int i =0; i<ballBotArray.length; i++){
            BallBot otherBallBot; //This is another ball Bot that our main ball Bot may run into
            otherBallBot = ballBotArray [i]; 
            if(otherBallBot != null && otherBallBot != ballBot){  //This is making sure that the other ball bot is an actual ball Bot
                double currentDistance = distanceBetweenPoints(firstPoint, otherBallBot.getPoint()); //This is the distance between the Ball Bot and the other ball Bot right now
                double nextDistance = distanceBetweenPoints(nextPoint, otherBallBot.getPoint()); //This is their future distance
                if(currentDistance <= ballBot.getRadius() + otherBallBot.getRadius() && nextDistance <= currentDistance){
                    return otherBallBot;  //This is saying what will happen if the BallBot must bounce off the other Ball Bot
                }
            }
        }
        return null; //This is saying it won't have to bounce off and can keep moving
    }

    public static void newRunner(){ //This is the new runner activity 2 asked for
        BallWorld ballWorld2 = new BallWorld(600, 600);
        double  x = 0.0; 
        double y = 0.0; 
        TGPoint entrancePoint = new TGPoint(x, y); 
        BallBot [] ballBotArray = new BallBot [10]; //This is creating the ballBot Array activity 2 asked for
        BallRunner ballRunnerObject = new BallRunner (); 
        while(true){
            if(ballRunnerObject.entranceClear( ballBotArray, entrancePoint) == true){ //This is to check whether there's space for a new Ball bot in the Ball World
                if(ballRunnerObject.findFreeBallBotIndex(ballBotArray) != ballBotArray.length ){ //This is checking if there's a free space in the Ball Bot Array
                    int freeBallBotIndex = ballRunnerObject.findFreeBallBotIndex(ballBotArray); 
                    ballBotArray[freeBallBotIndex] = new BallBot(ballWorld2, entrancePoint, Math.random() *360, (int)(Math.random()*30)); //This is creating a new BallBot to fill in the null value 
                     //the math random at the end makes the radiuses of all the ball bots different lengths, so they're all different sizes
                    ballBotArray[freeBallBotIndex].setColor((int)(Math.random()*31)); //This gives all the balls random colors
                    ballBotArray[freeBallBotIndex].setPixelsPerSecond((int)(Math.random()*70)+30); //This gives all the balls random speeds
                }
            }
            for(int index=0; index<ballBotArray.length; index++){
                if(ballBotArray[index] != null){ //This is checking that there's a ball bot to move around (instead of a null value)
                    if(ballBotArray[index].canMoveForward(ballWorld2)){ 
                       if(ballRunnerObject.ballBotToBounceOff(ballBotArray [index], ballBotArray) == null){ //This is making sure the ball bot doesn't have to bounce off any other ball Bot
                            ballBotArray[index].moveForward();}
                          else{
                            ballBotArray[index].setHeading(ballBotArray[index].getHeading()%360+90); //This is resetting the heading of the ball bot if it does bounce off something
                            ballBotArray[index].moveForward();}
                    }else{ //This is resetting the heading of the ball Bot if it hits off a wall
                        ballBotArray[index].setHeading(ballBotArray[index].getHeading()%360+90);
                        ballBotArray[index].moveForward();

                    }
                }
            } 
        }
    }
}

