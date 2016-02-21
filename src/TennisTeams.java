import java.util.Arrays;

public class TennisTeams {
    public static void main(String[] args) {
        int[] team1 = {3, 1, 2};
        int[] team2 = {4, 1, 1};
        System.out.println(maximizePoints(team1, team2));
    }

    static int maximizePoints(int[] team1, int[] team2) {
        Arrays.sort(team1);
        Arrays.sort(team2);
        int max = 0;
        for (int i = 0; i < team1.length; i++) {
            if (team1[i] - team2[i] > 0) max += 1;
            else {
                for (int j = i + 1; j < team1.length; j++) {
                    if (team1[j] - team2[i] > 0) {
                        max += 1;
                        int temp = team1[i];
                        team1[i] = team1[j];
                        team1[j] = temp;
                        break;
                    }
                }
            }
        }

        return max;
    }
}
