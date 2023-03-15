import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
public class EnemyAction {
    private String desc;
    private ActionListener action;
    public EnemyAction(String s, ActionListener a){
        desc = s;
        action = a;
    }
    public String getDesc(){
        return desc;
    }
    public ActionListener getAction(){
        return action;
    }
    public void setDesc(String f){
        desc = f;
    }
}
