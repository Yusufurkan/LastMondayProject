package restAsured;

import org.hamcrest.MatcherAssert;
import org.hamcrest.collection.IsCollectionWithSize;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import  org.hamcrest.CoreMatchers.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.*;

public class TestHamcrest {

    @Test
    public void testWithHamcrest() {

        assertThat(4, equalTo(4) );

        assertThat("abc", is("abc") );


        assertThat("abc", not(equalTo("abcd")) );

        assertThat("abC", containsString("b"));


        List<Integer> lst = Arrays.asList(2,3,4);

//        assertThat(lst, everyItem(greaterThan(1)));

        assertThat(lst , IsCollectionWithSize.<Integer>hasSize(4));


    }

}
