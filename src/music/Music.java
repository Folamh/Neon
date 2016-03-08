package music;

import java.util.ArrayList;

import ddf.minim.*;
import processing.core.PApplet;

public class Music {
	PApplet p;
	Minim minim;
	int prevGameState;
	ArrayList<AudioPlayer> songs;
	AudioPlayer song;
	int curSong;
	int[] menu;
	int[] game;

	public Music(PApplet p) {
		this.p = p;
		minim = new Minim(this.p);
		songs = new ArrayList<AudioPlayer>();
		prevGameState = 0;
		curSong = 0;

		menu = new int[4];
		game = new int[9];

		menu[0] = 2;
		menu[1] = 3;
		menu[2] = 5;
		menu[3] = 9;

		game[0] = 0;
		game[1] = 1;
		game[2] = 6;
		game[3] = 8;
		game[4] = 10;
		game[5] = 11;
		game[6] = 12;
		game[7] = 13;
		game[8] = 14;

		// Add songs to class
		song = this.minim.loadFile("Resources/Music/AmplitudeProblem_IntoTheNight.wav");
		songs.add(song);// 0
		System.out.println("0");
		song = this.minim.loadFile("Resources/Music/BadassWolfShirt_EverythingWillBeJustFire.wav");
		songs.add(song);// 1
		System.out.println("1");
		song = this.minim.loadFile("Resources/Music/Home_Pyxis.wav");
		songs.add(song);// 2
		System.out.println("2");
		song = this.minim.loadFile("Resources/Music/Home_WereFinallyLanding.wav");
		songs.add(song);// 3
		System.out.println("3");
		song = this.minim.loadFile("Resources/Music/Megalopolis_Love.wav");
		songs.add(song);// 4
		System.out.println("4");
		song = this.minim.loadFile("Resources/Music/ThreeChainLinks_CrackedStreetsAndBrokenWindows.wav");
		songs.add(song);// 5
		System.out.println("5");
		song = this.minim.loadFile("Resources/Music/ThreeChainLinks_DanceHarder.wav");
		songs.add(song);// 6
		System.out.println("6");
		song = this.minim.loadFile("Resources/Music/ThreeChainLinks_DriftingOff.wav");
		songs.add(song);// 7
		System.out.println("7");
		song = this.minim.loadFile("Resources/Music/ThreeChainLinks_DriveFast.wav");
		songs.add(song);// 8
		System.out.println("8");
		song = this.minim.loadFile("Resources/Music/ThreeChainLinks_HappiestDays.wav");
		songs.add(song);// 9
		System.out.println("9");
		song = this.minim.loadFile("Resources/Music/ThreeChainLinks_HeavyTraffic.wav");
		songs.add(song);// 10
		System.out.println("10");
		song = this.minim.loadFile("Resources/Music/ThreeChainLinks_HotNightsInLosAngeles.wav");
		songs.add(song);// 11
		System.out.println("11");
		song = this.minim.loadFile("Resources/Music/ThreeChainLinks_ItCan'tBeBargainedWith.wav");
		songs.add(song);// 12
		System.out.println("12");
		song = this.minim.loadFile("Resources/Music/ThreeChainLinks_Labyrinth.wav");
		songs.add(song);// 13
		System.out.println("13");
		song = this.minim.loadFile("Resources/Music/ThreeChainLinks_RagingStreets.wav");
		songs.add(song);// 14
		System.out.println("14");
	}

	public void doShit(int gameState) {//Does  all the functions needed for music to play
		if(gameState != 0){
			if (!songs.get(curSong).isPlaying()) {// If the song ends it will play another
				System.out.println("Working?");
				changeSong(gameState);
			}
			else{
				if (gameState != prevGameState) {
					System.out.println("Working?");
					prevGameState = gameState;
					changeSong(gameState);
				}
			}
		}
		else{
			reset();//if it is at the start screen don't play anything
		}
	}

	void play() {// plays the song
		songs.get(curSong).play();
	}

	void reset() {//resets and pauses the songs
		songs.get(curSong).rewind();
		songs.get(curSong).pause();
	}

	void changeSong(int gameState) {//Main switch for changing music.
		p.randomSeed(p.millis());
		int rand = 0;
		switch (gameState) {
		case 1:
		case 2:
			rand = (int) p.random(0, 3);
			reset();
			curSong = menu[rand];
			play();
			break;
		case 3:
			reset();
			curSong = 4;
			play();
			break;
		case 4:
			rand = (int) p.random(0, 8);
			reset();
			curSong = game[rand];
			play();
			break;
		case 5:
			reset();
			curSong = 7;
			play();
			break;
		}
	}
}
