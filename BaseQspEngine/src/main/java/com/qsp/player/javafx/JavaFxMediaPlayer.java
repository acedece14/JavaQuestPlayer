package com.qsp.player.javafx;

import com.qsp.player.vi.AudioInterface;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 * 音乐播放器
 */
public class JavaFxMediaPlayer implements AudioInterface {

    private MediaPlayer mediaPlayer;

    public JavaFxMediaPlayer() {

    }

    private JavaFxMediaPlayer(String path) {
        this.mediaPlayer = new MediaPlayer(new Media(path));
        mediaPlayer.play();
    }

    @Override
    public AudioInterface createNewPlayer(String fileName) {
        return new JavaFxMediaPlayer(fileName);
    }

    @Override
    public void stop() {
        mediaPlayer.stop();
    }

    @Override
    public void release() {
        mediaPlayer.stop();
        mediaPlayer = null;
    }

    @Override
    public void pause() {
        mediaPlayer.pause();
    }

    @Override
    public void start() {
        mediaPlayer.play();
    }

    @Override
    public boolean isPlaying() {
        return mediaPlayer.isAutoPlay();
    }

    @Override
    public void setVolume(float sysVolume) {
        mediaPlayer.setVolume(sysVolume);
    }
}
