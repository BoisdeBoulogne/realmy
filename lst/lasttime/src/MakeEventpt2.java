import javax.sound.midi.*;

public class MakeEventpt2 implements ControllerEventListener {

    public static void main (String[] args){
        MakeEventpt2 Exp = new MakeEventpt2();
        Exp.go();
    }

    public void go(){
        try {
            Sequencer sequencer = MidiSystem.getSequencer();
            sequencer.open();

            int[] eventINeed = {127};
            sequencer.addControllerEventListener(this,eventINeed);

            Sequence seq = new Sequence(Sequence.PPQ,4);
            Track track = seq.createTrack();

            for (int i= 5;i<60;i+=4){
                track.add(MakeE(144,1,i,100,i));

                track.add(MakeE(176,1,127,0,i));

                track.add(MakeE(128,1,i,100,i+2));
            }

            sequencer.setSequence(seq);
            sequencer.setTempoInBPM(220);
            sequencer.start();
        }   catch (Exception ex){ex.printStackTrace();}
    }
    public void controlChange(ShortMessage event){
        System.out.println("la");
    }

    public static MidiEvent MakeE (int comd,int chan,int one,int two,int tick) {
        MidiEvent curr = null;
        try {
            ShortMessage a = new ShortMessage();
            a.setMessage(comd, chan, one, two);
            curr = new MidiEvent(a, tick);
        } catch (Exception e) {

        }
        return curr;
    }
}
