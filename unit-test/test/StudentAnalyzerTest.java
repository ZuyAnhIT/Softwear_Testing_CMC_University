package test;

import src.StudentAnalyzer;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;
import java.util.Collections;

public class StudentAnalyzerTest {
    
    private final StudentAnalyzer analyzer = new StudentAnalyzer();

    @Test
    public void testCountExcellentStudents() {
        // Trường hợp bình thường
        assertEquals(2, analyzer.countExcellentStudents(Arrays.asList(9.0, 8.5, 7.0, 11.0, -1.0)));
        // Trường hợp biên: Rỗng
        assertEquals(0, analyzer.countExcellentStudents(Collections.emptyList()));
    }

    @Test
    public void testCalculateValidAverage() {
        // Trường hợp bình thường (11.0 và -1.0 bị loại)
        assertEquals(8.17, analyzer.calculateValidAverage(Arrays.asList(9.0, 8.5, 7.0, 11.0, -1.0)), 0.01);
        
        // Trường hợp biên: Danh sách rỗng
        assertEquals(0.0, analyzer.calculateValidAverage(Collections.emptyList()), 0.01);

        // Trường hợp ngoại lệ: Toàn điểm sai
        assertEquals(0.0, analyzer.calculateValidAverage(Arrays.asList(-5.0, 15.0)), 0.01);
    }
}
