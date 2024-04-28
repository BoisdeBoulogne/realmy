import javax.sound.midi.*;
import javax.swing.*;
import java.awt.*;

class MyPanel extends JPanel implements ControllerEventListener
{
    boolean Msg = false;
    public void controlChange(ShortMessage event){
        Msg = true;
        repaint();
    }
    public void paintComponent(Graphics g){
        if (Msg){
            Graphics2D g2 = (Graphics2D) g;
            int r = (int) (Math.random()*250);
            int gr = (int) (Math.random()*250);
            int b = (int) (Math.random()*250);

            g.setColor(new Color(r,gr,b));

            int ht = (int) (Math.random()*120+10);
            int width = (int)  (Math.random()*120+10);
            int x = (int) (Math.random()*40+10);
            int y = (int) (Math.random()*40+10);
            g.fillRect(x,y,width,ht);
            Msg = false;
        }
    }
}
public class ListenEventswithgui {
    static JFrame mygui = new JFrame("My first music video");
    static MyPanel ml;
    public static void main (String []args){
        ListenEventswithgui Mine = new ListenEventswithgui();
        Mine.go();
    }
    public void setUp(){
        ml = new MyPanel();
        mygui.setContentPane(ml);
        mygui.setSize(300,300);
        mygui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mygui.setVisible(true);
    }
    public void go(){
        setUp();

        try {
            Sequencer sequencer = MidiSystem.getSequencer();
            sequencer.open();
            sequencer.addControllerEventListener(ml,new int[] {127});
            Sequence seq = new Sequence(Sequence.PPQ,4);
            Track track = seq.createTrack();

            int r = 0;
            for (int i= 0;i<60;i+=4){

                r += (int) (Math.random()*50+1);
                track.add(MakeE(144,1,r,100,i));
                track.add(MakeE(176,1,127,0,i));
                track.add(MakeE(128,1,r,100,i+2));
            }
            sequencer.setSequence(seq);
            sequencer.setTempoInBPM(120);
            sequencer.start();
        }   catch (Exception ex){
            ex.printStackTrace();
        }
    }
    public static MidiEvent MakeE (int comd,int chan,int one,int two,int tick) {
        MidiEvent curr = null;
        try {
            ShortMessage a = new ShortMessage();
            a.setMessage(comd, chan, one, two);
            curr = new MidiEvent(a, tick);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return curr;
    }
}