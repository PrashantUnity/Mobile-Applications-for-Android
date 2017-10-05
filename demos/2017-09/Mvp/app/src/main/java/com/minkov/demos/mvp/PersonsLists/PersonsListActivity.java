package com.minkov.demos.mvp.PersonsLists;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.minkov.demos.mvp.base.BaseDrawerActivity;
import com.minkov.demos.mvp.PersonDetails.PersonDetailsActivity;
import com.minkov.demos.mvp.PersonDetails.PersonDetailsContacts;
import com.minkov.demos.mvp.PersonDetails.PersonDetailsFragment;
import com.minkov.demos.mvp.R;
import com.minkov.demos.mvp.models.Person;
import com.minkov.demos.mvp.ui.ActivityUtils;

import javax.inject.Inject;

/**
 * The implementation of the {@link com.minkov.demos.mvp.PersonsLists.PersonsListContracts.View} class
 */
public class PersonsListActivity extends BaseDrawerActivity implements PersonsListContracts.Router {
    // CHECKSTYLE:OFF
    @SuppressWarnings("CheckStyle")
    @Inject
    PersonsListContracts.Presenter mPersonsListPresenter;
    // CHECKSTYLE:ON

    @SuppressWarnings("CheckStyle")
    @Inject
    PersonDetailsContacts.Presenter mPersonDetailsPresenter;

    private PersonsListFragment mPersonsListView;
    private PersonDetailsFragment mPersonDetailsView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_persons_list);
        super.onCreate(savedInstanceState);

        mPersonsListView = PersonsListFragment.newInstance();

        ActivityUtils.attachFragment(getSupportFragmentManager(), R.id.frame_list, mPersonsListView);

        if (isTablet()) {
            mPersonDetailsView = PersonDetailsFragment.newInstance();
            mPersonDetailsView.setPresenter(mPersonDetailsPresenter);
            ActivityUtils.attachFragment(getSupportFragmentManager(), R.id.frame_details, mPersonDetailsView);
        }
    }

    @Override
    protected void onResume() {
        mPersonsListPresenter.setRouter(this);
        mPersonsListView.setPresenter(mPersonsListPresenter);
        super.onResume();
    }

    @Override
    public void showDetails(Person obj) {
        if (isTablet()) {
            mPersonDetailsPresenter.setPersonId(obj.getId());
        } else {
            Intent intent = new Intent(this, PersonDetailsActivity.class);
            intent.putExtra(PersonDetailsActivity.EXTRA_PERSON_KEY, obj.getId());
            startActivity(intent);
        }
    }

    private boolean isTablet() {
        return ActivityUtils.containsView(this, R.id.frame_details);
    }

    @Override
    protected Toolbar getToolbar() {
        return findViewById(R.id.toolbar);
    }
}
