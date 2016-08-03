
package com.example.ice.battingpractice2;


	import android.content.Context;
	import android.graphics.Bitmap;
	import android.graphics.BitmapFactory;
    import android.graphics.drawable.Drawable;

public class Pitcher  {
    private Bitmap img;     // the image of pitcher
    private int coordX = 0; // the x coordinate at the canvas
    private int coordY = 0; // the y coordinate at the canvas
    private int mPitcher[];         // gives every ball his own id, for now not necessary
    private static int count = 1;
    private boolean goRight = true;
    private boolean goDown = true;
    private boolean rightHanded = true;
    private int MAX_PITCHER = 107;
    private int frame = 0;

    private int width = 200;
    private int height = 200;
	 
    public Pitcher() {

        mPitcher = new int[120];
        mPitcher[0] = R.drawable.p1;
        mPitcher[1] = R.drawable.p2;
        mPitcher[2] = R.drawable.p3;
        mPitcher[3] = R.drawable.p4;
        mPitcher[4] = R.drawable.p5;
        mPitcher[5] = R.drawable.p6;
        mPitcher[6] = R.drawable.p7;
        mPitcher[7] = R.drawable.p8;
        mPitcher[8] = R.drawable.p9;
        mPitcher[9] = R.drawable.p10;
        mPitcher[10] = R.drawable.p11;
        mPitcher[11] = R.drawable.p12;
        mPitcher[12] = R.drawable.p13;
        mPitcher[13] = R.drawable.p14;
        mPitcher[14] = R.drawable.p15;
        mPitcher[15] = R.drawable.p16;
        mPitcher[16] = R.drawable.p17;
        mPitcher[17] = R.drawable.p18;
        mPitcher[18] = R.drawable.p19;
        mPitcher[19] = R.drawable.p20;
        mPitcher[20] = R.drawable.p21;
        mPitcher[21] = R.drawable.p22;
        mPitcher[22] = R.drawable.p23;
        mPitcher[23] = R.drawable.p24;
        mPitcher[24] = R.drawable.p25;
        mPitcher[25] = R.drawable.p26;
        mPitcher[26] = R.drawable.p27;
        mPitcher[27] = R.drawable.p28;
        mPitcher[28] = R.drawable.p29;
        mPitcher[29] = R.drawable.p30;
        mPitcher[30] = R.drawable.p31;
        mPitcher[31] = R.drawable.p32;
        mPitcher[32] = R.drawable.p33;
        mPitcher[33] = R.drawable.p34;
        mPitcher[34] = R.drawable.p35;
        mPitcher[35] = R.drawable.p36;
        mPitcher[36] = R.drawable.p37;
        mPitcher[37] = R.drawable.p38;
        mPitcher[38] = R.drawable.p39;
        mPitcher[39] = R.drawable.p40;
        mPitcher[40] = R.drawable.p41;
        mPitcher[41] = R.drawable.p42;
        mPitcher[42] = R.drawable.p43;
        mPitcher[43] = R.drawable.p44;
        mPitcher[44] = R.drawable.p45;
        mPitcher[45] = R.drawable.p46;
        mPitcher[46] = R.drawable.p47;
        mPitcher[47] = R.drawable.p48;
        mPitcher[48] = R.drawable.p49;
        mPitcher[49] = R.drawable.p50;
        mPitcher[50] = R.drawable.p51;
        mPitcher[51] = R.drawable.p52;
        mPitcher[52] = R.drawable.p53;
        mPitcher[53] = R.drawable.p54;
        mPitcher[54] = R.drawable.p55;
        mPitcher[55] = R.drawable.p56;
        mPitcher[56] = R.drawable.p57;
        mPitcher[57] = R.drawable.p58;
        mPitcher[58] = R.drawable.p59;
        mPitcher[59] = R.drawable.p60;
        mPitcher[60] = R.drawable.p61;
        mPitcher[61] = R.drawable.p62;
        mPitcher[62] = R.drawable.p63;
        mPitcher[63] = R.drawable.p64;
        mPitcher[64] = R.drawable.p65;
        mPitcher[65] = R.drawable.p66;
        mPitcher[66] = R.drawable.p67;
        mPitcher[67] = R.drawable.p68;
        mPitcher[68] = R.drawable.p69;
        mPitcher[69] = R.drawable.p70;
        mPitcher[70] = R.drawable.p71;
        mPitcher[71] = R.drawable.p72;
        mPitcher[72] = R.drawable.p73;
        mPitcher[73] = R.drawable.p74;
        mPitcher[74] = R.drawable.p75;
        mPitcher[75] = R.drawable.p76;
        mPitcher[76] = R.drawable.p77;
        mPitcher[77] = R.drawable.p78;
        mPitcher[78] = R.drawable.p79;
        mPitcher[79] = R.drawable.p80;
        mPitcher[80] = R.drawable.p81;
        mPitcher[81] = R.drawable.p82;
        mPitcher[82] = R.drawable.p83;
        mPitcher[83] = R.drawable.p84;
        mPitcher[84] = R.drawable.p85;
        mPitcher[85] = R.drawable.p86;
        mPitcher[86] = R.drawable.p87;
        mPitcher[87] = R.drawable.p88;
        mPitcher[88] = R.drawable.p89;
        mPitcher[89] = R.drawable.p90;
        mPitcher[90] = R.drawable.p91;
        mPitcher[91] = R.drawable.p92;
        mPitcher[92] = R.drawable.p93;
        mPitcher[93] = R.drawable.p94;
        mPitcher[94] = R.drawable.p95;
        mPitcher[95] = R.drawable.p96;
        mPitcher[96] = R.drawable.p97;
        mPitcher[97] = R.drawable.p98;
        mPitcher[98] = R.drawable.p99;
        mPitcher[99] = R.drawable.p100;
        mPitcher[100] = R.drawable.p101;
        mPitcher[101] = R.drawable.p102;
        mPitcher[102] = R.drawable.p103;
        mPitcher[103] = R.drawable.p104;
        mPitcher[104] = R.drawable.p105;
        mPitcher[105] = R.drawable.p106;
        mPitcher[106] = R.drawable.p107;
        mPitcher[107] = R.drawable.p108;
    }

    public int next() {
        frame += 2;
        if (frame >= MAX_PITCHER)
            frame = MAX_PITCHER;
        return mPitcher[frame];
    }

    public void begin() {
        frame = 0;
    }

    public int getFrame() {
			return frame;
		}
		
    void setX(int newValue) {
	        coordX = newValue;
	    }
		
    public int getX() {
			return coordX;
		}

    void setHeight(int newValue) {
	        height = newValue;
	   }
		
    public int getHeight() {
			return height;
		}

    void setWidth(int newValue) {
        width = newValue;
    }

    public int getWidth() {
        return width;
    }

    void setY(int newValue) {
        coordY = newValue;
    }

    public int getY() {
        return coordY;
    }


    public Drawable getDrawable(Context c) {
        Drawable ball = c.getResources().getDrawable(this.next());
        ball.setBounds((int) this.getX(), (int) this.getY(), this.getX() + width, this.getY() + height);
        return ball;
    }
		
    public Bitmap getBitmap() {
			return img;
		}
		
    public void movePitcher(int goX, int goY) {
			// check the borders, and set the direction if a border has reached
        if (coordX > 270){
            goRight = false;
        }
        if (coordX < 0){
            goRight = true;
        }
        if (coordY > 400){
            goDown = false;
        }
        if (coordY < 0){
            goDown = true;
        }
        // move the x and y
        if (goRight){
            coordX += goX;
        }else
        {
            coordX -= goX;
        }
        if (goDown){
            coordY += goY;
        }else {
            coordY -= goY;
        }
			
		}
		public void update() {
		}


	}

