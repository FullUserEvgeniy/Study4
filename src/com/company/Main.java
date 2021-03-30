package com.company;
class QueueFullException extends Exception{
    int size;
    QueueFullException(int s){size=s;}
    public String toString(){
        return "\nОчередь заполнена.";
    }
}
class QueueEmptyException extends Exception{
    public String toString() {
        return "\nОчередь пуста";
    }
}
class FixedQueue implements ICharQ{
    private char q[];
    private int putloc,getloc;
    public FixedQueue(int size){
        q=new char[size];
        putloc=getloc=0;
    }
    public void put(char ch) throws QueueFullException {
        if (putloc==q.length) throw new QueueFullException(q.length);
        q[putloc++]=ch;
    }
    public char get() throws QueueEmptyException{
        if (getloc==putloc) throw new QueueEmptyException();
        return q[getloc++];
    }
}
interface ICharQ{
    void put(char ch)throws QueueFullException;
    char get()throws QueueEmptyException;
}
public class Main {
    public static void main(String[] args) {
        FixedQueue q=new FixedQueue(10);
        char ch;
        int i;
        try{
            for (i=0;i<11;i++){
                System.out.print("Попытка сохранения: "+(char)('A'+i));
                q.put((char)('A'+i));
                System.out.println(" - OK");
            }
        }catch (QueueFullException exc){
            System.out.println(exc);
        }
        try {
            for (i=0;i<11;i++){
                System.out.print("Получение очередного символа: ");
                ch=q.get();
                System.out.println(ch);
            }
        }catch (QueueEmptyException exc){
            System.out.println(exc);
        }
    }
}
