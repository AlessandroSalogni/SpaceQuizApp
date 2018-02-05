package com.example.android.spacequiz.parse_data;

import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.example.android.spacequiz.observer.Observer;
import com.example.android.spacequiz.recycler_view.view_holder.instantiable_view_holder.CheckBoxColumnViewHolder;
import com.example.android.spacequiz.recycler_view.view_holder.instantiable_view_holder.CheckBoxTableViewHolder;
import com.example.android.spacequiz.recycler_view.view_holder.instantiable_view_holder.FreeAnswerViewHolder;
import com.example.android.spacequiz.recycler_view.view_holder.instantiable_view_holder.RadioButtonColumnViewHolder;
import com.example.android.spacequiz.recycler_view.view_holder.instantiable_view_holder.RadioButtonTrueFalseViewHolder;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Alessandro
 */
public class ParseData {
    private static final String QUESTION_REGEX = "\\[Question]";
    private static final String ANSWER_REGEX = "\\[Answer]";
    private static final String CORRECT_ANSWER_REGEX = "\\[Correct]";
    private static final String DRAWABLE_REGEX = "\\[Drawable]";
    private static final String TYPE_REGEX = "\\[Type]";
    private static final Map<String, Class<? extends RecyclerView.ViewHolder>> TYPE_CARD_VIEW = new HashMap<>();

    private final RecyclerView.ViewHolder viewHolder;
    String[] data;

    static {
        TYPE_CARD_VIEW.put("RadioButtonColumn", RadioButtonColumnViewHolder.class);
        TYPE_CARD_VIEW.put("RadioButtonTrueFalse", RadioButtonTrueFalseViewHolder.class);
        TYPE_CARD_VIEW.put("CheckBoxColumn", CheckBoxColumnViewHolder.class);
        TYPE_CARD_VIEW.put("CheckBoxTable", CheckBoxTableViewHolder.class);
        TYPE_CARD_VIEW.put("FreeAnswer", FreeAnswerViewHolder.class);
    }

    public ParseData(String[] data, ViewGroup parent, Observer obs) {
        this.data = data;
        viewHolder = createViewHolder(parent, obs);
    }

    public String getQuestion() {
        for(String questionData : data)
            if(questionData.matches("^" + QUESTION_REGEX + ".*$"))
                return questionData.replaceFirst(QUESTION_REGEX, "");

        throw new IllegalStateException("Error: Question not found");
    }

    public ArrayList<String> getAnswers() {
        ArrayList<String> answers = new ArrayList<>();
        
        for(String questionData : data)
            if(questionData.matches("^" + ANSWER_REGEX + ".*$"))
                answers.add(questionData.replaceFirst(ANSWER_REGEX, ""));
        
        deleteCorrectKeyword(answers);

        if(answers.isEmpty())
            throw new IllegalStateException("Error: Answers not found");

        return answers;
    }

    public ArrayList<Integer> getCorrectAnswerPosition() {
        ArrayList<Integer> correctAnswerPosition = new ArrayList<>();

        for(int i=0, answerFound=0; i < data.length; i++)
            if(data[i].matches("^" + ANSWER_REGEX + ".*$")) {
                if(data[i].matches("^.*" + CORRECT_ANSWER_REGEX + ".*$"))
                    correctAnswerPosition.add(answerFound);

                answerFound++;
            }

        if(correctAnswerPosition.isEmpty())
            throw new IllegalStateException("Error: There aren't correct answers");

        return correctAnswerPosition;
    }

    public Drawable getImage(AppCompatActivity activity) {
        for(String questionData : data)
            if(questionData.matches("^" + DRAWABLE_REGEX + ".*$")) {
                questionData = questionData.replaceFirst(DRAWABLE_REGEX, "");
                return activity.getResources().getDrawable(activity.getResources().getIdentifier(questionData, "drawable", activity.getPackageName()));
            }

        return null;
    }

    public RecyclerView.ViewHolder getViewHolder() {
        return viewHolder;
    }

    private void deleteCorrectKeyword(ArrayList<String> answers) {
        for(int i=0; i < answers.size(); i++) {
            String answer = answers.get(i);

            if(answer.matches("^" + CORRECT_ANSWER_REGEX + ".*$"))
                answers.set(i, answer.replaceFirst(CORRECT_ANSWER_REGEX, ""));
        }
    }

    private RecyclerView.ViewHolder createViewHolder(ViewGroup parent, Observer obs) {
        for(String questionData : data)
            if(questionData.matches("^" + TYPE_REGEX + ".*$")) {
                questionData = questionData.replaceFirst(TYPE_REGEX, "");

                try {
                    Class<? extends  RecyclerView.ViewHolder> viewHolderClass = TYPE_CARD_VIEW.get(questionData.substring(questionData.indexOf("[") + 1, questionData.indexOf("]")));
                    Constructor<? extends RecyclerView.ViewHolder> viewHolderConstructor = viewHolderClass.getConstructor(ViewGroup.class, ParseData.class, Observer.class);
                    return viewHolderConstructor.newInstance(parent, this, obs);
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }

        throw new IllegalStateException("Error: Type card view not found");
    }
}
