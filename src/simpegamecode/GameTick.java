package simpegamecode;

class GameTick implements Runnable {
    public int Tick = 60;
    public long TPS;
    public long Tmr;
    
    public void run() {
        
        while (Main.BlnRun) {
            Tmr = System.nanoTime();
            try {
                Thread.sleep(1000 / Tick);
            } catch (Exception e) {
                
            }
            TPS = 1000000000/ (System.nanoTime() - Tmr);
        }
    }

}
