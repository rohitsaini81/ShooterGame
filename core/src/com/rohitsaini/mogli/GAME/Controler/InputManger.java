package com.rohitsaini.mogli.GAME.Controler;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputProcessor;

public class InputManger implements Input {
    public InputManger(){
        Gdx.input.setInputProcessor(this.getInputProcessor());
    }

    @Override
    public float getAccelerometerX() {
        return 0;
    }

    @Override
    public float getAccelerometerY() {
        return 0;
    }

    @Override
    public float getAccelerometerZ() {
        return 0;
    }

    @Override
    public float getGyroscopeX() {
        return 0;
    }

    @Override
    public float getGyroscopeY() {
        return 0;
    }

    @Override
    public float getGyroscopeZ() {
        return 0;
    }

    @Override
    public int getMaxPointers() {
        return 0;
    }

    @Override
    public int getX() {
        return 0;
    }

    @Override
    public int getX(int i) {
        return 0;
    }

    @Override
    public int getDeltaX() {
        return 0;
    }

    @Override
    public int getDeltaX(int i) {
        return 0;
    }

    @Override
    public int getY() {
        return 0;
    }

    @Override
    public int getY(int i) {
        return 0;
    }

    @Override
    public int getDeltaY() {
        return 0;
    }

    @Override
    public int getDeltaY(int i) {
        return 0;
    }

    @Override
    public boolean isTouched() {
        return false;
    }

    @Override
    public boolean justTouched() {
        return false;
    }

    @Override
    public boolean isTouched(int i) {
        return false;
    }

    @Override
    public float getPressure() {
        return 0;
    }

    @Override
    public float getPressure(int i) {
        return 0;
    }

    @Override
    public boolean isButtonPressed(int i) {
        return false;
    }

    @Override
    public boolean isButtonJustPressed(int i) {
        return false;
    }

    @Override
    public boolean isKeyPressed(int i) {
        System.out.println("it should work");
        return false;
    }

    @Override
    public boolean isKeyJustPressed(int i) {
        return false;
    }

    @Override
    public void getTextInput(TextInputListener textInputListener, String s, String s1, String s2) {

    }

    @Override
    public void getTextInput(TextInputListener textInputListener, String s, String s1, String s2, OnscreenKeyboardType onscreenKeyboardType) {

    }

    @Override
    public void setOnscreenKeyboardVisible(boolean b) {

    }

    @Override
    public void setOnscreenKeyboardVisible(boolean b, OnscreenKeyboardType onscreenKeyboardType) {

    }

    @Override
    public void vibrate(int i) {

    }

    @Override
    public void vibrate(int i, boolean b) {

    }

    @Override
    public void vibrate(int i, int i1, boolean b) {

    }

    @Override
    public void vibrate(VibrationType vibrationType) {

    }

    @Override
    public float getAzimuth() {
        return 0;
    }

    @Override
    public float getPitch() {
        return 0;
    }

    @Override
    public float getRoll() {
        return 0;
    }

    @Override
    public void getRotationMatrix(float[] floats) {

    }

    @Override
    public long getCurrentEventTime() {
        return 0;
    }

    @Override
    public void setCatchBackKey(boolean b) {

    }

    @Override
    public boolean isCatchBackKey() {
        return false;
    }

    @Override
    public void setCatchMenuKey(boolean b) {

    }

    @Override
    public boolean isCatchMenuKey() {
        return false;
    }

    @Override
    public void setCatchKey(int i, boolean b) {

    }

    @Override
    public boolean isCatchKey(int i) {
        return false;
    }

    @Override
    public void setInputProcessor(InputProcessor inputProcessor) {

    }

    @Override
    public InputProcessor getInputProcessor() {
        return null;
    }

    @Override
    public boolean isPeripheralAvailable(Peripheral peripheral) {
        return false;
    }

    @Override
    public int getRotation() {
        return 0;
    }

    @Override
    public Orientation getNativeOrientation() {
        return null;
    }

    @Override
    public void setCursorCatched(boolean b) {

    }

    @Override
    public boolean isCursorCatched() {
        return false;
    }

    @Override
    public void setCursorPosition(int i, int i1) {

    }
}
