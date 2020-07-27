import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;


public class LiftMiniProject {
    static Lift lift1;
    public static void main(String[] args) {
        lift1 = new Lift(0);

        // TODO : To be enhanced to incorporate multithreading
        new Thread(()->{
                Scanner scanner = new Scanner(System.in);
            while(true) {
                System.out.print("Enter a floor number : ");
                    while (!scanner.hasNext()) {
                        try {
                        scanner.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    if (scanner.hasNext()) {
                        Integer nextFloor = scanner.nextInt();
                    if(nextFloor==-1) return;
                        lift1.feed(nextFloor);
                        lift1.move();
                    }
            }
        }).start();
    }
}

class Lift {
    int lastFloor;
    Queue<Integer> liftRoute;

    public Lift(int lastFloor) {
        this.lastFloor = lastFloor;
        this.liftRoute = new LinkedList<>();
    }

    public synchronized void move() {
        if(lastFloor<liftRoute.peek())
            System.out.println("Last floor = " + lastFloor +" ; Lift moving up to floor " + (lastFloor=liftRoute.poll()));
        else if (lastFloor>liftRoute.peek())
            System.out.println("Last floor = " + lastFloor +" ; Lift moving down to floor "+(lastFloor=liftRoute.poll()));
        else
            System.out.println("Lift has stopped");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void feed(int floorNum) {
        liftRoute.add(floorNum);
        System.out.println("Floor "+floorNum+" added to the route");
    }
}
