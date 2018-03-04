package com.minstudio.core;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class GridRenderer {

    private SpriteBatch batch;
    private int startX;
    private int startY;
    private int gridNoX;
    private int gridNoY;
    private int gridSize;
    private float gridThicknessPx;
    private Texture img;
    private TextureRegion rect;

    public GridRenderer(SpriteBatch batch, int startX, int startY, int gridNoX, int gridNoY, int gridSize, float gridThicknessPx) {
        img = Resources.getTexture("sprites/white-dot.png");
        rect = new TextureRegion(img, 0, 0, 1, 1);
        this.batch = batch;
        this.startX = startX;
        this.startY = startY;
        this.gridNoX = gridNoX;
        this.gridNoY = gridNoY;
        this.gridSize = gridSize;
        this.gridThicknessPx = gridThicknessPx;
    }

    public void render() {
        for (int x = 0; x < gridNoX; x++) {
            for (int y = 0; y < gridNoY; y++) {
                renderSquare(startX + x * gridSize, startY + y * gridSize);
            }
        }
    }

    /**
     * Render a square, while updating coordinates at the btm left.
     */
    private void renderSquare(float x, float y) {
        batch.draw(rect, x, y, gridSize, gridThicknessPx);  //btm
        batch.draw(rect, x, y + gridSize, gridSize, gridThicknessPx);  //top
        batch.draw(rect, x, y, gridThicknessPx, gridSize);  //left
        batch.draw(rect, x + gridSize, y, gridThicknessPx, gridSize);   //right
    }
}
