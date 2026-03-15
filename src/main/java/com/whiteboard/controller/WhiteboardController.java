package com.whiteboard.controller;

import com.whiteboard.model.DrawMessage;
import com.whiteboard.model.Stroke;
import com.whiteboard.repository.StrokeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*; // Added for PostMapping and RequestBody
import java.util.List;

@Controller
public class WhiteboardController {

    @Autowired
    private StrokeRepository strokeRepository;

    /**
     * This handles LIVE collaboration. 
     * It sends the drawing to everyone else immediately, 
     * but NO LONGER saves to the database automatically.
     */
    @MessageMapping("/draw")
    @SendTo("/topic/draw")
    public DrawMessage draw(DrawMessage message) {
        return message; 
    }

    /**
     * This handles MANUAL saving.
     * When you click the save button on the UI, the frontend sends 
     * the list of new points here to be stored in PostgreSQL.
     */
    @PostMapping("/strokes/save")
    @ResponseBody
    public String saveStrokes(@RequestBody List<DrawMessage> messages) {
        try {
            if (messages == null || messages.isEmpty()) {
                return "No data to save";
            }

            for (DrawMessage msg : messages) {
                Stroke stroke = new Stroke();
                stroke.setX(msg.getX());
                stroke.setY(msg.getY());
                stroke.setColor(msg.getColor());
                stroke.setSize(msg.getSize());
                stroke.setType(msg.getType());
                strokeRepository.save(stroke);
            }
            return "Saved " + messages.size() + " points to database!";
        } catch (Exception e) {
            return "Error saving to database: " + e.getMessage();
        }
    }

    /**
     * Loads the saved history when a user refreshes the page.
     */
    @GetMapping("/strokes")
    @ResponseBody
    public List<Stroke> getStrokes() {
        return strokeRepository.findAllByOrderByIdAsc();
    }

    /**
     * Optional: Clear everything from the database
     */
    @PostMapping("/strokes/clear-db")
    @ResponseBody
    public String clearDatabase() {
        strokeRepository.deleteAll();
        return "Database wiped successfully";
    }
}