import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
class leetcodeTest {

    leetcode leetcode = new leetcode();
    @Test
    void main() {
    }

    @Test
    void isUnique() {
    }

    @Test
    void checkPermutation() {
    }

    @Test
    void compressString() {
    }

    @Test
    void isFlipedString() {
        boolean flipedString = leetcode.isFlipedString("waterbottle"
                , "erbottlewat");
        System.out.println(flipedString);
    }

    @Test
    void findWhetherExistsPath() {

    }

    @Test
    void oneEditAway() {
        boolean b = leetcode.oneEditAway("teachy", "teacher");
        System.out.println(b);
    }

    @Test
    void rotate() {
        int[][] ints = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        leetcode.rotate(ints);

    }

    @Test
    void setZeroes() {
        int[][] ints = {{1, 2, 3}, {4, 0, 6}, {7, 8, 9}};
        leetcode.setZeroes(ints);
    }


}