import java.util.List;

/**
 * Phân tích điểm số sinh viên
 * Cung cấp các phương thức để đếm sinh viên xuất sắc và tính điểm trung bình
 * 
 * @author Testing Team
 * @version 2.0
 */
public class StudentAnalyzer {
    
    // Constants - Dễ bảo trì và tránh magic numbers
    private static final double MIN_VALID_SCORE = 0.0;
    private static final double MAX_VALID_SCORE = 10.0;
    private static final double EXCELLENT_THRESHOLD = 8.0;
    
    /**
     * Đếm số sinh viên xuất sắc (điểm >= 8.0)
     * Chỉ tính các điểm hợp lệ (0-10, không null)
     * 
     * @param scores Danh sách điểm số của sinh viên
     * @return Số lượng sinh viên xuất sắc, trả về 0 nếu list null/rỗng
     * 
     * @example
     * <pre>
     * countExcellentStudents(Arrays.asList(9.0, 8.5, 7.0)) => 2
     * countExcellentStudents(Arrays.asList(7.0, 6.5))     => 0
     * countExcellentStudents(null)                        => 0
     * </pre>
     */
    public int countExcellentStudents(List<Double> scores) {
        if (scores == null || scores.isEmpty()) {
            return 0;
        }
        
        int count = 0;
        for (Double score : scores) {
            if (isValidScore(score) && score >= EXCELLENT_THRESHOLD) {
                count++;
            }
        }
        return count;
    }
    
    /**
     * Tính điểm trung bình của các điểm hợp lệ
     * Bỏ qua các điểm không hợp lệ (< 0, > 10, null)
     * 
     * @param scores Danh sách điểm số của sinh viên
     * @return Điểm trung bình, trả về 0.0 nếu không có điểm hợp lệ nào
     * 
     * @example
     * <pre>
     * calculateValidAverage(Arrays.asList(8.0, 9.0, 10.0))      => 9.0
     * calculateValidAverage(Arrays.asList(5.0, -1.0, 8.0))      => 6.5
     * calculateValidAverage(Arrays.asList(-1.0, 11.0))          => 0.0
     * </pre>
     */
    public double calculateValidAverage(List<Double> scores) {
        if (scores == null || scores.isEmpty()) {
            return 0.0;
        }
        
        double sum = 0;
        int validCount = 0;
        
        for (Double score : scores) {
            if (isValidScore(score)) {
                sum += score;
                validCount++;
            }
        }
        
        return validCount == 0 ? 0.0 : sum / validCount;
    }
    
    /**
     * Kiểm tra điểm có hợp lệ không
     * Điểm hợp lệ phải: không null, >= 0, <= 10
     * 
     * @param score Điểm cần kiểm tra
     * @return true nếu điểm hợp lệ, false nếu không
     * 
     * @implNote Method này được sử dụng nội bộ để tránh code trùng lặp
     */
    private boolean isValidScore(Double score) {
        return score != null 
            && score >= MIN_VALID_SCORE 
            && score <= MAX_VALID_SCORE;
    }
}
