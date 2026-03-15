package com.whiteboard.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "strokes") // This creates/maps to the 'strokes' table in Postgres
@Data // This automatically creates Getters and Setters via Lombok
public class DrawMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String roomId;
    private double x;
    private double y;
    private String color;
    private int size;
    
    // CRITICAL: This stores whether the point was a 'START' (click) or 'MOVE' (dragging)
    private String type; 

    // Explicit Getters and Setters (in case Lombok isn't working)
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    
    public double getX() { return x; }
    public void setX(double x) { this.x = x; }

    public double getY() { return y; }
    public void setY(double y) { this.y = y; }

    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }

    public int getSize() { return size; }
    public void setSize(int size) { this.size = size; }
}