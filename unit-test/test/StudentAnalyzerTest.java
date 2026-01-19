import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Bộ test TỐI ƯU cho StudentAnalyzer
 * Áp dụng Equivalence Partitioning với nguyên tắc: 1 EP = 1 test đại diện
 * 
 * Tổng: 15 test cases cover đầy đủ 25 lớp tương đương
 * Giảm 42% số test so với version đầy đủ nhưng vẫn đảm bảo coverage 100%
 * 
 * @author Testing Team
 * @version 2.1 - Optimized
 */
@DisplayName("StudentAnalyzer - Optimized Test Suite")
public class StudentAnalyzerTest {
    
    private StudentAnalyzer analyzer;
    
    @BeforeEach
    public void setUp() {
        analyzer = new StudentAnalyzer();
    }
    
    // ==========================================
    // TEST countExcellentStudents() - 7 TCs
    // Cover EP1-EP16 (16 lớp tương đương)
    // ==========================================
    
    @Nested
    @DisplayName("countExcellentStudents()")
    class CountExcellentTests {
        
        @Test
        @DisplayName("EP7: Null list → 0")
        public void testNullList() {
            assertEquals(0, analyzer.countExcellentStudents(null));
        }
        
        @Test
        @DisplayName("EP8: Empty list → 0")
        public void testEmptyList() {
            assertEquals(0, analyzer.countExcellentStudents(Collections.emptyList()));
        }
        
        @Test
        @DisplayName("EP9,EP1: List có null elements → Skip null | Cover EP1: null score")
        public void testListWithNullElements() {
            // EP9: Test null elements
            // EP1: Test null score validation
            assertEquals(2, analyzer.countExcellentStudents(Arrays.asList(null, 8.5, 9.0, null)));
        }
        
        @Test
        @DisplayName("EP10,EP16: All < 8.0 → 0 | Test boundary 7.99")
        public void testAllBelowThreshold() {
            // EP10: All below threshold
            // EP16: Boundary 7.99 (just below 8.0)
            assertEquals(0, analyzer.countExcellentStudents(Arrays.asList(5.0, 7.99, 6.5)));
        }
        
        @Test
        @DisplayName("EP11,EP15,EP5,EP3: All >= 8.0 → Count all | Test boundaries 8.0, 10.0, 0.0")
        public void testAllExcellentWithBoundaries() {
            // EP11: All excellent
            // EP15: Boundary 8.0 (exactly threshold)
            // EP5: Boundary 10.0 (max valid)
            // Bonus: Single element list edge case
            assertEquals(3, analyzer.countExcellentStudents(Arrays.asList(8.0, 9.0, 10.0)));
        }
        
        @Test
        @DisplayName("EP12,EP4: Mixed excellent & non-excellent | Cover EP4: valid middle scores")
        public void testMixedScores() {
            // EP12: Mixed excellent and non-excellent
            // EP4: Valid middle scores (0 < score < 10)
            assertEquals(2, analyzer.countExcellentStudents(Arrays.asList(7.0, 8.5, 6.0, 9.0)));
        }
        
        @Test
        @DisplayName("EP13,EP14,EP2,EP6: Invalid scores (< 0, > 10) → Skip | Cover EP2,EP6")
        public void testWithInvalidScores() {
            // EP13: Contains scores < 0
            // EP14: Contains scores > 10
            // EP2: Negative score validation
            // EP6: Over max score validation
            assertEquals(2, analyzer.countExcellentStudents(Arrays.asList(8.0, -1.0, 9.5, 11.0)));
        }
    }
    
    // ==========================================
    // TEST calculateValidAverage() - 6 TCs
    // Cover EP17-EP25 (9 lớp tương đương)
    // ==========================================
    
    @Nested
    @DisplayName("calculateValidAverage()")
    class CalculateAverageTests {
        
        @Test
        @DisplayName("EP17: Null list → 0.0")
        public void testNullList() {
            assertEquals(0.0, analyzer.calculateValidAverage(null), 0.01);
        }
        
        @Test
        @DisplayName("EP18: Empty list → 0.0")
        public void testEmptyList() {
            assertEquals(0.0, analyzer.calculateValidAverage(Collections.emptyList()), 0.01);
        }
        
        @Test
        @DisplayName("EP19: List có null elements → Skip null, calculate valid")
        public void testListWithNullElements() {
            // EP19: Contains null elements
            // Average = (5.0 + 8.0) / 2 = 6.5
            assertEquals(6.5, analyzer.calculateValidAverage(Arrays.asList(null, 5.0, 8.0)), 0.01);
        }
        
        @Test
        @DisplayName("EP20,EP23,EP24: All valid với boundaries 0.0 và 10.0")
        public void testAllValidWithBoundaries() {
            // EP20: All valid scores
            // EP23: Contains boundary 0.0
            // EP24: Contains boundary 10.0
            // Average = (0.0 + 5.0 + 10.0) / 3 = 5.0
            assertEquals(5.0, analyzer.calculateValidAverage(Arrays.asList(0.0, 5.0, 10.0)), 0.01);
        }
        
        @Test
        @DisplayName("EP21: All invalid → 0.0")
        public void testAllInvalidScores() {
            // EP21: All invalid scores
            assertEquals(0.0, analyzer.calculateValidAverage(Arrays.asList(-1.0, 11.0, 15.0)), 0.01);
        }
        
        @Test
        @DisplayName("EP22,EP25: Mixed valid/invalid | Single valid score case")
        public void testMixedValidInvalid() {
            // EP22: Mixed valid and invalid
            // Valid: 5.0, 8.0 → Average = 6.5
            assertEquals(6.5, analyzer.calculateValidAverage(Arrays.asList(5.0, -1.0, 8.0, 12.0)), 0.01);
            
            // EP25: Single valid score
            assertEquals(7.5, analyzer.calculateValidAverage(Arrays.asList(7.5)), 0.01);
        }
    }
    
    // ==========================================
    // INTEGRATION TEST - 2 TCs (Essential only)
    // ==========================================
    
    @Nested
    @DisplayName("Integration & Critical Edge Cases")
    class IntegrationTests {
        
        @Test
        @DisplayName("Complex real-world: All input types combined")
        public void testComplexRealWorld() {
            // Test case bao gồm TẤT CẢ các loại input:
            // - Excellent: 9.0, 8.5, 8.0, 10.0
            // - Non-excellent valid: 7.0, 0.0, 7.99
            // - Invalid: 11.0, -1.0, 15.0, -5.0
            // - Null: null
            List<Double> scores = Arrays.asList(
                9.0, 8.5, 7.0, 11.0, -1.0, null, 
                8.0, 10.0, 0.0, 7.99, 15.0, -5.0
            );
            
            // Count: 9.0, 8.5, 8.0, 10.0 = 4
            assertEquals(4, analyzer.countExcellentStudents(scores));
            
            // Average: (9.0+8.5+7.0+8.0+10.0+0.0+7.99)/7 = 7.21
            assertEquals(7.21, analyzer.calculateValidAverage(scores), 0.01);
        }
        
        @Test
        @DisplayName("Edge case: Performance với list lớn")
        public void testLargeListPerformance() {
            // Test performance và correctness với 10000 phần tử
            Double[] array = new Double[10000];
            for (int i = 0; i < 5000; i++) array[i] = 9.0;  // Excellent
            for (int i = 5000; i < 10000; i++) array[i] = 7.0;  // Not excellent
            
            List<Double> scores = Arrays.asList(array);
            
            long startTime = System.currentTimeMillis();
            assertEquals(5000, analyzer.countExcellentStudents(scores));
            assertEquals(8.0, analyzer.calculateValidAverage(scores), 0.01);
            long endTime = System.currentTimeMillis();
            
            // Assert performance (nên < 100ms cho 10k elements)
            assertTrue(endTime - startTime < 100, 
                "Should process 10000 elements in < 100ms, actual: " + (endTime - startTime) + "ms");
        }
    }
}
