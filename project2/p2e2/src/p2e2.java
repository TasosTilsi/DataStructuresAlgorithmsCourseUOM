import java.util.ArrayList;
import java.util.Arrays;

public class p2e2 {

    public static void main(String[] args) {

        ArrayList<Student> students =new ArrayList<>();

        while (!StdIn.isEmpty()) {
            String name = StdIn.readString(); //reads input from Standard Input
            Date date = new Date(StdIn.readString());
            double grade = StdIn.readDouble();
            int year = StdIn.readInt();
            Student student = new Student(name,date,grade,year); //creating a Student object
            students.add(student);
//            StdOut.println(student);
        }

        Object[] studentsArray = students.toArray();


        // sort by name order Ascending and print results
        StdOut.println("By name ascending");
        StdOut.println("----------");
        MergeX.sort(studentsArray, Student.NAME_ORDER_ASC);
        for (int i = 0; i < studentsArray.length; i++)
            StdOut.println(studentsArray[i]);
        StdOut.println();

        // sort by name order Descending and print results
        StdOut.println("By name descending");
        StdOut.println("----------");
        MergeX.sort(studentsArray, Student.NAME_ORDER_DESC);
        for (int i = 0; i < studentsArray.length; i++)
            StdOut.println(studentsArray[i]);
        StdOut.println();

        // sort by date and order ascending and print results
        StdOut.println("By date ascending");
        StdOut.println("----------");
        MergeX.sort(studentsArray, Student.DATE_ORDER_ASC);
        for (int i = 0; i < studentsArray.length; i++)
            StdOut.println(studentsArray[i]);
        StdOut.println();

        // sort by grade order ascending and print results
        StdOut.println("By grade ascending");
        StdOut.println("----------");
        MergeX.sort(studentsArray, Student.GRADE_ORDER_ASC);
        for (int i = 0; i < studentsArray.length; i++)
            StdOut.println(studentsArray[i]);
        StdOut.println();

        // sort by year order ascending and print results
        StdOut.println("By year ascending");
        StdOut.println("----------");
        MergeX.sort(studentsArray, Student.YEAR_ORDER_ASC);
        for (int i = 0; i < studentsArray.length; i++)
            StdOut.println(studentsArray[i]);
        StdOut.println();

        // sort by name and print results
        StdOut.println("By grade and year ascending");
        StdOut.println("----------");
        MergeX.sort(studentsArray, Student.GRADE_AND_YEAR_ORDER_ASC);
        for (int i = 0; i < studentsArray.length; i++)
            StdOut.println(studentsArray[i]);
        StdOut.println();

        Student me = new Student("Tilsizoglou",new Date("09/27/2021"),8.5,2023);

        // now, sort by name relative to Tilsizoglou
        StdOut.println("By Tilsizoglou");
        StdOut.println("----------");
        MergeX.sort(studentsArray, me.SPECIFIC_NAME_ORDER_ASC);
        for (int i = 0; i < studentsArray.length; i++)
            StdOut.println(studentsArray[i]);
        StdOut.println();

        String fred = "Fred";

        StdOut.println("me.equals(students.contains(students.get(0))):   " + (me.equals(students.get(0))));
        StdOut.println("me.equals(new Student):   " + (me.equals(new Student("TilsizoglouAnastasios",new Date("10/12/2021"),10,2023))));
        StdOut.println("me.equals(fred):  " + (me.equals(fred)));

    }
}


