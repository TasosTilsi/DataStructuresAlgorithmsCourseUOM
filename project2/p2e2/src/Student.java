/******************************************************************************
 *  Compilation:  javac Student.java
 *  Execution:    java Student
 *  Dependencies: StdOut.java
 *
 *  Illustrates implementation of a Comparator and equals() method.
 *
 *  % By name
 *  ----------
 *  2 Alice
 *  1 Bob
 *  2 Carol
 *  1 Dave
 *  2 Eve
 *  3 Frank
 *  1 Grant
 *  3 Helia
 *  3 Isaac
 *  1 Jen
 *  1 Kevin
 *  2 Larry
 *
 *  By section
 *  ----------
 *  1 Bob
 *  1 Dave
 *  1 Grant
 *  1 Jen
 *  1 Kevin
 *  2 Alice
 *  2 Carol
 *  2 Eve
 *  2 Larry
 *  3 Frank
 *  3 Helia
 *  3 Isaac
 *
 *  By Kevin
 *  ----------
 *  1 Kevin
 *  2 Larry
 *  2 Alice
 *  1 Bob
 *  2 Carol
 *  1 Dave
 *  2 Eve
 *  3 Frank
 *  1 Grant
 *  3 Helia
 *  3 Isaac
 *  1 Jen
 *
 ******************************************************************************/

import java.util.Arrays;
import java.util.Comparator;

/**
 * The {@code Student} class is an immutable data type to encapsulate a
 * student name and section number. It is used to illustrate various
 * comparators.
 * <p>
 * For additional documentation,
 * see <a href="https://algs4.cs.princeton.edu/12oop">Section 1.2</a> of
 * <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 *
 * @author Robert Sedgewick
 * @author Kevin Wayne
 */
public class Student {

    public enum SortOrder {ASCENDING, DESCENDING}

    public static final Comparator<Student> NAME_ORDER_ASC = new NameOrder(SortOrder.ASCENDING); //1)
    public static final Comparator<Student> NAME_ORDER_DESC = new NameOrder(SortOrder.DESCENDING); //2)
    public static final Comparator<Student> DATE_ORDER_ASC = new DateOrder(SortOrder.ASCENDING); //3)
    public static final Comparator<Student> DATE_ORDER_DESC = new DateOrder(SortOrder.DESCENDING);
    public static final Comparator<Student> GRADE_ORDER_ASC = new GradeOrder(SortOrder.ASCENDING); //4)
    public static final Comparator<Student> GRADE_ORDER_DESC = new GradeOrder(SortOrder.DESCENDING);
    public static final Comparator<Student> YEAR_ORDER_ASC = new YearOrder(SortOrder.ASCENDING); //5)
    public static final Comparator<Student> YEAR_ORDER_DESC = new YearOrder(SortOrder.DESCENDING);
    public static final Comparator<Student> GRADE_AND_YEAR_ORDER_ASC = new GradeAndYearOrder(SortOrder.ASCENDING); //6)
    public static final Comparator<Student> GRADE_AND_YEAR_ORDER_DESC = new GradeAndYearOrder(SortOrder.DESCENDING);
    public final Comparator<Student> SPECIFIC_NAME_ORDER_ASC = new RelativeNameOrder();

    /**
     * A comparator for comparing students in ascending order by section number.
     */
//    public static final Comparator<Student> SECTION_ORDER = new SectionOrder();

    private final String name;
    private Date date; //dateEnteredInUniversity
    private double grade; //degree grade
    private int year; //yearOfDegreeReceived
//    private int section;


    /**
     * Initializes a new student from the given arguments.
     *
     * @param name    the name of this student
     * @param //section the section number of this student
     */
    /*public Student(String name, int section) {
        this.name = name;
        this.section = section;
    }*/

    public Student(String name, Date date, double grade, int year) {
        this.name = name;
        this.date = date;
        this.grade = grade;
        this.year = year;
    }

    /**
     * Returns a comparator for comparing students in lexicographic order by name.
     *
     * @return a {@link Comparator} for comparing students in lexicographic order by name
     */
    private static Comparator<Student> byNameOrder(SortOrder order) {
        return new NameOrder(order);
    }

    private static Comparator<Student> byDateOrder(SortOrder order) {
        return new DateOrder(order);
    }

    private static Comparator<Student> byGradeOrder(SortOrder order) {
        return new GradeOrder(order);
    }

    private static Comparator<Student> byYearOrder(SortOrder order) {
        return new YearOrder(order);
    }

    private static Comparator<Student> byGradeAndYearOrder(SortOrder order) {
        return new GradeAndYearOrder(order);
    }

//    private static Comparator<Student> byGradeAndYearOrder(String order) {
//        return new GradeAndYearOrder(order);
//    }

    /**
     * Returns a comparator for comparing students in numerical order by section.
     *
     * @return a {@link Comparator} for comparing students in numerical order by section
     */
    /*private static Comparator<Student> bySectionOrder() {
        return new SectionOrder();
    }*/

    // compare students by names, starting at this student's name
    // and wrapping around alphabetically
    private Comparator<Student> byRelativeNameOrder() {
        return new RelativeNameOrder();
    }

    // comparator to sort by name
    private static class NameOrder implements Comparator<Student> {
        SortOrder order;
        public NameOrder(SortOrder order) {
            this.order = order;
        }

        public int compare(Student a, Student b) {
            if (!order.equals(SortOrder.DESCENDING)) {
                return a.name.compareTo(b.name);
            }else {
                return b.name.compareTo(a.name);
            }
        }
    }

    // comparator to sort by date
    private static class DateOrder implements Comparator<Student> {
        SortOrder order;
        public DateOrder(SortOrder order) {
            this.order = order;
        }

        public int compare(Student a, Student b) {
            if (!order.equals(SortOrder.DESCENDING)) {
                return a.date.compareTo(b.date);
            }else{
                return b.date.compareTo(a.date);
            }

        }
    }

    // comparator to sort by grade
    private static class GradeOrder implements Comparator<Student> {
        SortOrder order;
        public GradeOrder(SortOrder order) {
            this.order = order;
        }

        public int compare(Student a, Student b) {
            if (!order.equals(SortOrder.DESCENDING)) {
                return Double.compare(a.grade,b.grade);
            }else{
                return Double.compare(b.grade,a.grade);
            }

        }
    }

    // comparator to sort by year
    private static class YearOrder implements Comparator<Student> {
        SortOrder order;
        public YearOrder(SortOrder order) {
            this.order = order;
        }

        public int compare(Student a, Student b) {
            if (!order.equals(SortOrder.DESCENDING)) {
                return Integer.compare(a.year,b.year);
            }else{
                return Integer.compare(b.year,a.year);
            }

        }
    }

    // comparator to sort by grade and year
    private static class GradeAndYearOrder implements Comparator<Student> {
        SortOrder order;
        public GradeAndYearOrder(SortOrder order) {
            this.order = order;
        }

        public int compare(Student a, Student b) {
            if (!order.equals(SortOrder.DESCENDING)) {
                return byGradeOrder(SortOrder.ASCENDING).thenComparing(byYearOrder(SortOrder.ASCENDING)).compare(a,b);
            }else{
                if (Double.compare(b.grade,a.grade)==0)
                    return byGradeOrder(SortOrder.DESCENDING).thenComparing(byYearOrder(SortOrder.DESCENDING)).compare(b,a);
                else
                    return byGradeOrder(SortOrder.DESCENDING).thenComparing(byYearOrder(SortOrder.DESCENDING)).compare(a,b);
            }
        }
    }

    // comparator to sort by section
    /*private static class SectionOrder implements Comparator<Student> {
        public int compare(Student a, Student b) {
            return a.section - b.section;
        }
    }*/

    // comparator to sort by name with this name first
    // illustrates the use of a non-static comparator
    private class RelativeNameOrder implements Comparator<Student> {
        public int compare(Student a, Student b) {
            if (a.name.compareTo(b.name) == 0) return 0;
            if (a.name.compareTo(name) == 0) return -1;
            if (b.name.compareTo(name) == 0) return +1;
            if ((a.name.compareTo(name) < 0) && (b.name.compareTo(name) > 0))
                return +1;
            if ((a.name.compareTo(name) > 0) && (b.name.compareTo(name) < 0))
                return -1;
            return a.name.compareTo(b.name);
        }
    }

    /**
     * Compares this student to the specified student.
     *
     * @param other the other student
     * @return {@code true} if this student equals {@code other};
     * {@code false} otherwise
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) return true;
        if (other == null) return false;
        if (other.getClass() != this.getClass()) return false;
        Student that = (Student) other;
        return (this.name.equals(that.name) && (this.year==that.year) && (this.grade==that.grade));
    }

    /*@Override
    public int hashCode() {
        return 31 * section + name.hashCode();
    }*/

    @Override
    public int hashCode() {
        int hash = 1;
        hash = 31*hash + name.hashCode();
        hash = 31*hash + ((Integer)year).hashCode();
        hash = 31*hash + ((Double) grade).hashCode();
        hash = 31*hash + date.hashCode();
        return hash;
    }

    /*@Override
    public String toString() {
        return section + " " + name;
    }*/

    @Override
    public String toString() {
        return String.format("%s: %d: %.2f: %s",name,year,grade,date);
    }


    /**
     * Unit tests the {@code Student} data type.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {

        // create an array of students
        Student alice = new Student("Alice",new Date("02/05/2021"),9.7,2021);
        Student bob = new Student("Bob",new Date("02/09/2021"),10,2024);
        Student carol = new Student("Carol",new Date("02/02/2021"),8.5,2027);
        Student dave = new Student("Dave",new Date("02/18/2021"),8.77,2019);
        Student eve = new Student("Eve",new Date("02/21/2021"),10,2022);
        Student tas = new Student("TAS",new Date("03/21/2021"),10,2018);
        Student aes = new Student("AES",new Date("04/21/2021"),9.7,2014);
        Student ars = new Student("ARS",new Date("05/21/2021"),8.77,2015);
        Student[] students = {
                eve, dave, carol, bob, alice,tas,aes,ars
        };

        // sort by name and print results
        StdOut.println("By name");
        StdOut.println("----------");
        Arrays.sort(students, Student.byNameOrder(SortOrder.ASCENDING));
        for (int i = 0; i < students.length; i++)
            StdOut.println(students[i]);
        StdOut.println();

        // sort by name and print results
        StdOut.println("By date");
        StdOut.println("----------");
        Arrays.sort(students, Student.byDateOrder(SortOrder.ASCENDING));
        for (int i = 0; i < students.length; i++)
            StdOut.println(students[i]);
        StdOut.println();

        // sort by name and print results
        StdOut.println("By grade");
        StdOut.println("----------");
        Arrays.sort(students, Student.byGradeOrder(SortOrder.ASCENDING));
        for (int i = 0; i < students.length; i++)
            StdOut.println(students[i]);
        StdOut.println();

        // sort by name and print results
        StdOut.println("By year");
        StdOut.println("----------");
        Arrays.sort(students, Student.byYearOrder(SortOrder.ASCENDING));
        for (int i = 0; i < students.length; i++)
            StdOut.println(students[i]);
        StdOut.println();

        // sort by name and print results
        StdOut.println("By grade and year desc");
        StdOut.println("----------");
        Arrays.sort(students, Student.byGradeAndYearOrder(SortOrder.DESCENDING));
        for (int i = 0; i < students.length; i++)
            StdOut.println(students[i]);
        StdOut.println();

        // sort by name and print results
        StdOut.println("By grade and year asc");
        StdOut.println("----------");
        Arrays.sort(students, Student.byGradeAndYearOrder(SortOrder.ASCENDING));
        for (int i = 0; i < students.length; i++)
            StdOut.println(students[i]);
        StdOut.println();

        // now, sort by name relative to eve
        StdOut.println("By eve");
        StdOut.println("----------");
        Arrays.sort(students, eve.byRelativeNameOrder());
        for (int i = 0; i < students.length; i++)
            StdOut.println(students[i]);
        StdOut.println();

        Student ali = new Student("Alice",new Date("02/05/2021"),9.7,2021);
        String fred = "Fred";

        StdOut.println("alice == ali:        " + (alice == ali));
        StdOut.println("alice.equals(ali):   " + (alice.equals(ali)));
        StdOut.println("alice.equals(bob):   " + (alice.equals(bob)));
        StdOut.println("alice.equals(fred):  " + (alice.equals(fred)));

    }

}