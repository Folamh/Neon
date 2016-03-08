package music;

import java.util.ArrayList;

import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
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
		song = this.minim.loadFile("Resources/Music/AmplitudeProblem_IntoTheNight.mp3");
		songs.add(song);// 0
		song = this.minim.loadFile("Resources/Music/BadassWolfShirt_EverythingWillBeJustFire.mp3");
		songs.add(song);// 1
		song = this.minim.loadFile("Resources/Music/Home_Pyxis.mp3");
		songs.add(song);// 2
		song = this.minim.loadFile("Resources/Music/Home_WereFinallyLanding.mp3");
		songs.add(song);// 3
		song = this.minim.loadFile("Resources/Music/Megalopolis_Love.mp3");
		songs.add(song);// 4
		song = this.minim.loadFile("Resources/Music/ThreeChainLinks_CrackedStreetsAndBrokenWindows.mp3");
		songs.add(song);// 5
		song = this.minim.loadFile("Resources/Music/ThreeChainLinks_DriftingOff.mp3");
		songs.add(song);// 6
		song = this.minim.loadFile("Resources/Music/ThreeChainLinks_DriveFast.mp3");
		songs.add(song);// 8
		song = this.minim.loadFile("Resources/Music/ThreeChainLinks_HappiestDays.mp3");
		songs.add(song);// 9
		song = this.minim.loadFile("Resources/Music/ThreeChainLinks_HeavyTraffic.mp3");
		songs.add(song);// 10
		song = this.minim.loadFile("Resources/Music/ThreeChainLinks_HotNightsInLosAngeles.mp3");
		songs.add(song);// 11
		song = this.minim.loadFile("Resources/Music/ThreeChainLinks_ItCan'tBeBargainedWith.mp3");
		songs.add(song);// 12
		song = this.minim.loadFile("Resources/Music/ThreeChainLinks_Labyrinth.mp3");
		songs.add(song);// 13
		song = this.minim.loadFile("Resources/Music/ThreeChainLinks_RagingStreets.mp3");
		songs.add(song);// 14
	}

	public void doShit(int gameState) {//Does  all the functions needed for music to play
		if(gameState != 0){
			songEnd(gameState);
			menuSwitch(gameState);
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

	void songEnd(int gameState) {
		if (!songs.get(curSong).isPlaying()) {// If the song ends it will play another
			changeSong(gameState);
		}
	}

	void menuSwitch(int gameState) {//If the game changes menu
		if (gameState != prevGameState) {
			changeSong(gameState);
			prevGameState = gameState;
		}
	}

	void changeSong(int gameState) {//Main switch for changing music.
		p.randomSeed(p.millis());
		int rand = 0;
		switch (gameState) {
		case 1:
		case 2:
			rand = (int) p.random(0, 3);
			if (curSong != menu[rand]) {
				System.out.println("working?");
				reset();
				curSong = menu[rand];
				play();
			}
			break;
		case 3:
			reset();
			curSong = 4;
			play();
			break;
		case 4:
			rand = (int) p.random(0, 8);
			if (curSong != game[rand]) {
				reset();
				curSong = game[rand];
				play();
			}
			break;
		case 5:
			reset();
			curSong = 7;
			play();
			break;
		}
	}
}
