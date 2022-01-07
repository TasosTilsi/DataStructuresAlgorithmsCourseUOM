public class UniQueue<T extends Comparable<T>> extends Queue<T> {
    private SET<T> set;

    public UniQueue(){
        set = new SET<T>();
    }

    @Override
    public void enqueue(T t) {

        if (set.isEmpty()) {
            set.add(t);
            super.enqueue(t);
        }else{
            if (!valueExists(t)){
                set.add(t);
                super.enqueue(t);
            }
        }
    }

    @Override
    public int size() {
        return super.size();
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (T t : this) {
            s.append(t);
            s.append('\n');
        }
        return s.toString();

        //one other solution for printing our unique elements is to print the elements
        // the set includes because they are identical but it is more appropriate to print a list like a queue as
        // this class tries to implement a unique Queue
        //
        //return set.toString();
    }

    public boolean valueExists(T t){
//        while (this.iterator().hasNext()){
//            if (this.iterator().next() == t){
//                return true;
//            }
//        }
//        return false;

        if (set.contains(t)){
            return true;
        }
        return false;
    }
}
