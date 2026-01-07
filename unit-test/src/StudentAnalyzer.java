package src;

import java.util.List;

public class StudentAnalyzer {

    /**
     * Phân tích điểm số và trả về số lượng học sinh đạt loại Giỏi.
     * @param scores danh sách điểm số từ 0 đến 10
     * @return số học sinh đạt loại Giỏi (>= 8.0)
     */
    public int countExcellentStudents(List<Double> scores) {
        if (scores == null || scores.isEmpty()) {
            return 0;
        }

        int count = 0;
        for (Double score : scores) {
            // Validate: Bỏ qua điểm âm hoặc lớn hơn 10
            if (score < 0 || score > 10) {
                continue;
            }
            // Check điều kiện giỏi
            if (score >= 8.0) {
                count++;
            }
        }
        return count;
    }

    // Placeholder cho hàm tiếp theo
    public double calculateValidAverage(List<Double> scores) {
        return 0.0;
    }
}