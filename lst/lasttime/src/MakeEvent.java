import javax.sound.midi.MidiEvent;
import javax.sound.midi.Sequencer;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.*;

class Player{
    public static void main (String[] args){
        try {
            Sequencer sequencer = MidiSystem.getSequencer();
            sequencer.open();

            Sequence seq = new Sequence(Sequence.PPQ,4);
            Track track = seq.createTrack();
            for (int i= 5;i<61;i+=4){
                track.add(MakeE(144,1,i,100,i));
                track.add(MakeE(128,1,i,100,i+4));
            }
            sequencer.setSequence(seq);
            sequencer.setTempoInBPM(220);
            sequencer.start();
        }   catch (Exception ex) {ex.printStackTrace();
    }}

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
