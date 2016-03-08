package music;

import java.util.ArrayList;

import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import processing.core.PApplet;

public class Music {
	PApplet p;
	int prevGameState;
	ArrayList<AudioPlayer> songs;
	int curSong;
	int[] menu;
	int[] game;

	public Music(Minim minim, PApplet p) {
		this.p = p;
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
		songs.add(minim.loadFile("Resources/Music/AmplitudeProblem_IntoTheNight.mp3"));// 0
		songs.add(minim.loadFile("Resources/Music/BadassWolfShirt_EverythingWillBeJustFire.mp3"));// 1
		songs.add(minim.loadFile("Resources/Music/Home_Pyxis.mp3"));// 2
		songs.add(minim.loadFile("Resources/Music/Home_WereFinallyLanding.mp3"));// 3
		songs.add(minim.loadFile("Resources/Music/Megalopolis_Love.mp3"));// 4
		songs.add(minim.loadFile("Resources/Music/ThreeChainLinks_CrackedStreetsAndBrokenWindows.mp3"));// 5
		songs.add(minim.loadFile("Resources/Music/ThreeChainLinks_DanceHarder.mp3"));// 6
		songs.add(minim.loadFile("Resources/Music/ThreeChainLinks_DriftingOff.mp3"));// 7
		songs.add(minim.loadFile("Resources/Music/ThreeChainLinks_DriveFast.mp3"));// 8
		songs.add(minim.loadFile("Resources/Music/ThreeChainLinks_HappiestDays.mp3"));// 9
		songs.add(minim.loadFile("Resources/Music/ThreeChainLinks_HeavyTraffic.mp3"));// 10
		songs.add(minim.loadFile("Resources/Music/ThreeChainLinks_HotNightsInLosAngeles.mp3"));// 11
		songs.add(minim.loadFile("Resources/Music/ThreeChainLinks_ItCan'tBeBargainedWith.mp3"));// 12
		songs.add(minim.loadFile("Resources/Music/ThreeChainLinks_Labyrinth.mp3"));// 13
		songs.add(minim.loadFile("Resources/Music/ThreeChainLinks_RagingStreets.mp3"));// 14
	}

	public void doShit(int gameState) {
		if(gameState != 0){
			songEnd(gameState);
			menuSwitch(gameState);
		}
		else{
			reset();
		}
	}

	void play() {// plays the song
		songs.get(curSong).play();
	}

	void reset() {
		songs.get(curSong).rewind();
		songs.get(curSong).pause();
	}

	void songEnd(int gameState) {
		if (!songs.get(curSong).isPlaying()) {// If the song ends it will play
												// the next track.
			changeSong(gameState);
		}
	}

	void menuSwitch(int gameState) {
		if (gameState != prevGameState) {
			changeSong(gameState);
			prevGameState = gameState;
		}
	}

	void changeSong(int gameState) {
		p.randomSeed(p.millis());
		int rand = 0;
		switch (gameState) {
		case 1:
		case 2:
			rand = (int) p.random(3);
			if (curSong != menu[rand]) {
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
			rand = (int) p.random(8);
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
