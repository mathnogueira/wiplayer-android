package pink.dcc.ufla.br.wiplayer.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import pink.dcc.ufla.br.wiplayer.actions.MainActions;
import pink.dcc.ufla.br.wiplayer.fragments.DevicesFragment;
import pink.dcc.ufla.br.wiplayer.fragments.HomeFragment;
import pink.dcc.ufla.br.wiplayer.fragments.MusicsFragment;
import pink.dcc.ufla.br.wiplayer.utils.FragmentReplacer;
import pink.dcc.ufla.br.wiplayer.R;

public class MainActivity extends AppCompatActivity {

    private FragmentReplacer fragmentReplacer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        configureReplacer();
    }

    private void configureReplacer() {
        fragmentReplacer = new FragmentReplacer(getSupportFragmentManager(), R.id.fragment_content);

        fragmentReplacer.replaceOn(MainActions.NAVIGATE_HOME, new HomeFragment());
        fragmentReplacer.replaceOn(MainActions.NAVIGATE_MUSIC, new MusicsFragment());
        fragmentReplacer.replaceOn(MainActions.NAVIGATE_DEVICES, new DevicesFragment());

        fragmentReplacer.defaultFragment(MainActions.NAVIGATE_HOME);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            int action = 0;
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    action = MainActions.NAVIGATE_HOME;
                    break;
                case R.id.navigation_music:
                    action = MainActions.NAVIGATE_MUSIC;
                    break;
                case R.id.navigation_devices:
                    action = MainActions.NAVIGATE_DEVICES;
                    break;
            }

            fragmentReplacer.dispatch(action);
            return true;
        }

    };

}
