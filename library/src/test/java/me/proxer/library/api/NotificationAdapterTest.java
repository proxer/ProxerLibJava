package me.proxer.library.api;

import com.squareup.moshi.JsonDataException;
import me.proxer.library.entity.notifications.NotificationInfo;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

/**
 * @author Ruben Gees
 */
public class NotificationAdapterTest {

    private NotificationInfoAdapter adapter;

    @Before
    public void setUp() {
        adapter = new NotificationInfoAdapter();
    }

    @Test
    public void testFromJson() {
        assertThat(adapter.fromJson(new int[]{0, 0, 1, 2, 3, 4}))
                .isEqualTo(new NotificationInfo(1, 2, 3, 4));
    }

    @Test
    public void testFromJsonInvalidSizeTooSmall() {
        assertThatThrownBy(() -> adapter.fromJson(new int[]{1, 2, 3, 4, 5}))
                .isInstanceOf(JsonDataException.class);
    }

    @Test
    public void testFromJsonInvalidSizeTooLarge() {
        assertThatThrownBy(() -> adapter.fromJson(new int[]{1, 2, 3, 4, 5, 6, 7}))
                .isInstanceOf(JsonDataException.class);
    }
}
