Here’s the extended report with a new section on how the recommended methods will help achieve the project’s goals, with examples and scenarios.

---

# Dataset Report for Quran Memorization Project

## 1. Dataset Overview

The dataset comprises three main tables that provide a comprehensive view of each student’s memorization and revision journey through the Quran. These tables are:

1. **Student Lesson History**:
   - Tracks individual recitation sessions for each student, detailing the specific verses recited, recitation type, and performance indicators.
   - Key columns include:
     - **student_id**: Identifies each student uniquely.
     - **start_verse_id** and **end_verse_id**: Specifies the range of verses recited in each session.
     - **pillar_id**: Indicates the type of recitation (e.g., 2 for new memorization, 3 for major revision, 4 for minor revision).
     - **date_of**: Date of the recitation session.
     - **letters_count** and **pages_count**: Captures the volume of recitation for each session.
   - Performance Metrics:
     - **letters_count** and **pages_count**: Important for tracking the memorization volume.
     - **pillar_id**: Useful for categorizing recitation types, helping the model distinguish between new memorization and revision sessions.

2. **Surahs**:
   - Stores static information about each surah (chapter), including the number of verses.
   - Key columns include:
     - **surah_id**: Unique identifier for each surah.
     - **name**: The name of the surah.
     - **no_verses**: Total number of verses in each surah.

3. **Verses**:
   - Provides verse-specific details, including order within the Quran and in each surah, as well as counts of letters and page locations.
   - Key columns include:
     - **verse_id**: Unique identifier for each verse.
     - **surah_id**: Links each verse to its respective surah.
     - **order_in_quraan**: The sequential order of the verse in the Quran.
     - **reverse_index**: The reverse order of the verses.
     - **page_no**: Page location of the verse.
     - **letters_count**: Total number of letters in each verse, used for pacing analysis.

---

## 2. Feature Relevance

### Important Columns
After analyzing the dataset’s structure, certain columns emerge as particularly useful for the model, while others may be less relevant or require transformation:

- **Essential Columns**:
  - **student_id**: Essential for tracking individual progress.
  - **start_verse_id** and **end_verse_id**: Useful for determining the range of verses and mapping out recitation patterns.
  - **pillar_id**: Provides insight into the type of recitation (new memorization, major, or minor revision).
  - **letters_count** and **pages_count**: Quantify each session’s recitation load, helping assess the student’s pace and memory retention.

- **Less Relevant Columns**:
  - **reverse_index** in the `Verses` table: Although this indicates the verse's reverse order, it may be less directly related to memorization performance.
  - **weight_on_page**: Offers a measure of verse prominence on the page but does not directly affect memorization patterns.

### Feature Transformations
For optimized model performance:
- **Surah-Based and Clustered Averages**: Calculating average memorization pace and error rate within each surah across students will support the model in estimating appropriate recitation amounts for students with no previous history in certain surahs.

---

## 3. Dataset Utilization in the Project

The dataset’s detailed structure allows it to support a personalized Quran memorization plan by providing:

- **Individual Historical Data**: Enables the model to analyze each student’s unique pace and performance trends.
- **Collective Cluster-Based Data**: Supports the model in adjusting plans based on similar students’ performance in specific surahs or pages.
- **Real-Time Performance Monitoring**: Logs each session’s recitation performance, allowing dynamic adjustments based on current learning progress.

---

## 4. Recommended Machine Learning Approach

Given the sequential and adaptive nature of Quran memorization, a **hybrid approach combining Long Short-Term Memory (LSTM) and Reinforcement Learning (RL)** is recommended.

### Why LSTM + RL?
- **LSTM**: LSTM models can effectively capture sequential dependencies, making them well-suited to predict the student’s performance trajectory based on previous recitation data.
- **Reinforcement Learning**: RL allows for real-time adaptation of daily plans based on each session's outcomes, supporting personalized adjustments that keep pace with the student’s performance.

### Key Model Inputs
- **LSTM Inputs**: Historical recitation sequences, with inputs such as `letters_count`, `pages_count`, and `pillar_id`.
- **RL Agent Inputs**: Recent performance metrics, including error rates and recitation acceptance, for dynamic adjustments.

---

## 5. How This Method Will Help Us in the Project

The hybrid LSTM + RL approach will empower the model to provide students with personalized Quran memorization and revision plans that evolve based on their learning patterns. By combining historical data with real-time performance monitoring, the model adapts to each student’s progress, reinforcing areas needing improvement and adjusting pace for areas where they excel. Here are some ways this method will contribute to the project’s goals:

### Adaptive Personalization
- **Example**: If a student consistently memorizes 10 verses daily with minimal errors, the model recognizes this and maintains or gradually increases their daily load. Conversely, if errors increase as the load is raised, the RL agent detects this and reduces the target to ensure accuracy.
- **Benefit**: This approach helps maintain a challenging yet manageable pace, balancing progress with retention.

### Real-Time Adjustments for Retention and Revision
- **Example**: If a student has not revised Surah Al-Fatiha in over a month, the RL model can prioritize this surah for review even if the student’s new memorization pace is strong. Additionally, if errors appear in previously mastered sections, the model increases revision frequency.
- **Benefit**: Students retain previously memorized verses while progressing in new memorization, ensuring they don’t forget earlier work due to a lack of review.

### Leveraging Cluster-Based Data for Students with Limited History
- **Example**: For a new student or one encountering a new surah without personal history, the model references similar students’ performance on that surah. For example, if similar students found Surah Yasin challenging, the model adjusts the initial recitation load conservatively, even if the student’s overall pace is high.
- **Benefit**: New and unfamiliar sections are approached with caution, preventing overload while still setting goals that align with the student’s potential.

### Adapting to Overall and Surah-Specific Paces
- **Example**: When a student revisits Surah Al-Ma’idah, both their overall pace and specific history with this surah are considered. If previous sessions in this surah involved higher error rates, the model sets a more moderate pace for this section, focusing on accuracy.
- **Benefit**: The model adapts to each surah’s unique demands, helping students manage challenging sections while keeping up their overall pace.

### Scenarios Illustrating the Model’s Impact

1. **Scenario 1: High-Performing Student Revisiting a Difficult Surah**  
   A student with a generally high memorization pace revisits Surah Al-Kahf, which they previously found challenging. The model uses the student’s general pace as a baseline but factors in higher error rates from previous recitations in this surah. As a result, the RL agent reduces the verse load for this session, ensuring a focus on accuracy over speed.

2. **Scenario 2: New Student with Limited History**  
   A new student without specific surah history begins Surah Al-Baqarah. The model references the average pace of similar students (in the same performance cluster) and finds that Surah Al-Baqarah typically takes longer for most students. It sets a conservative initial pace for this surah, gradually increasing as the student demonstrates proficiency.

3. **Scenario 3: Student with Inconsistent Performance**  
   If a student’s recent sessions show fluctuating performance with rising error counts, the RL agent reduces the number of new verses and increases revision sessions to reinforce retention. As consistency improves, the model restores the balance between memorization and revision.

---

## 6. How the Model Works

### Data Processing Workflow

1. **Preprocessing**:
   - Aggregate daily recitation data into sequences for each student, enabling the LSTM to learn from past performance patterns.

2. **LSTM Model Training**:
   - The LSTM is trained on these sequences to estimate an initial daily memorization and revision plan based on historical trends.

3. **RL Agent for Real-Time Adjustment**:
   - The RL agent dynamically adjusts the plan:
     - **New Surahs with No History**: Combines general pace with cluster-based averages to set a suitable starting target.
     - **Previously Memorized Surahs**: Integrates the student’s general and surah-specific pace for revisiting familiar sections.
   - The RL agent optimizes retention by adjusting quantities according to recent errors, skipped revisions, and time since last review.

---

## Conclusion

The hybrid LSTM + RL approach provides an adaptive and personalized solution for Quran memorization by leveraging historical, real-time, and collective performance data. Through targeted adjustments, the model supports each student’s unique learning journey, reinforcing challenging sections and optimizing retention. This approach ensures students progress confidently and retain knowledge, creating an effective and responsive Quran memorization experience.

Let me know if additional details are needed, or if you'd like to
