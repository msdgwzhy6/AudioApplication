package com.rangaofei.audioapplication.opengles;

import java.util.List;

/**
 * Created by rangaofei on 2018/4/28.
 */

public class Puck {
    private static final int POSITION_COMPONENT_COUNT = 3;
    public final float radius, height;

    private final VertexArray vertexArray;
    private final List<ObjectBuilder.DrawCommand> drawList;

    public Puck(float radius, float height, int numPointsAroundPuck) {
        ObjectBuilder.GenerateData generateData = ObjectBuilder.createPuck(
                new Geometry.Cylinder(new Geometry.Point(0f, 0f, 0f), radius, height),
                numPointsAroundPuck);
        this.radius = radius;
        this.height = height;
        vertexArray = new VertexArray(generateData.vertexData);
        drawList = generateData.drawList;
    }

    public void bindData(ColorShaderProgram colorProgram) {
        vertexArray.setVertexAttribPointer(
                0,
                colorProgram.getPositionAttributeLocation(),
                POSITION_COMPONENT_COUNT, 0);
    }

    public void draw() {
        for (ObjectBuilder.DrawCommand drawCommand : drawList) {
            drawCommand.draw();
        }
    }
}
