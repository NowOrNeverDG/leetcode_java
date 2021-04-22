public class AmazonInterview {
    //1041-Robot Bounded In Circle
    //The robot performs the instructions given in order, and repeats them forever.Return true if and only if there exists a circle in the plane such that the robot never leaves the circle.
    //Input: instructions = "GGLLGG"
    //Output: true
    public boolean isRobotBounded(String instructions) {
        int x = 0;
        int y = 0;
        int head = 0;
        char[] arr = instructions.toCharArray();

        for (int time=0; time<4; time++) {
            for (int ins = 0; ins < arr.length;ins++) {
                if (arr[ins] == 'L') head = (head+3)%4;
                else if (arr[ins] == 'R') head = (head+1)%4;
                else {
                    if (head==0) x++;
                    else if (head==1) y--;
                    else if (head==2) x--;
                    else if (head==3) y++;
                }
            }
        }
        return x==0&y==0;
    }


}
