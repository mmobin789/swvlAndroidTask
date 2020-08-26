package pse.at.swivl.base

import androidx.lifecycle.ViewModel

abstract class AppViewModel<View> : ViewModel() {
    private var view: View? = null

    /**
     * This method must be called by the UI to attach navigation to be monitored by the substituted view model to respond to UI specific event changes.
     * @param view reference to navigation
     */
    fun attachView(view: View) {
        this.view = view
    }

    /**
     * @returns current navigator if attached
     * @throws NullPointerException if not attached.
     */
    protected fun getView(): View {
        if (view == null)
            throw NullPointerException("View is null please call attach method 1st")

        return view!!
    }


    override fun onCleared() {
        view = null
    }
}