package view;

import java.util.ArrayList;

public abstract class Menu<T> {

    protected String title;
    protected ArrayList<T> mchoice;

    public Menu(String tt, String... mc) {
        this.title = tt;
        this.mchoice = new ArrayList<>();
        for (String s : mc) {
            mchoice.add((T) s);
        }
    }//phương thức khởi tạo này được sử dụng để khởi tạo một đối tượng Menu với tiêu đề (title) 
    //và danh sách các lựa chọn (mchoice) được cung cấp dưới dạng tham số. Các lựa chọn sẽ được thêm vào danh sách mchoice để sử dụng sau này.
    public void dispplay() { // print menu
        System.out.println(title);
        for (int i = 0; i < mchoice.size(); i++) {
            System.out.println((i + 1) + "." + mchoice.get(i));
        }
    }

    public abstract void execute();
    public void run(){
       dispplay();
       execute();
    }

}
