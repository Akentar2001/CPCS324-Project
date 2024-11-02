Why LSTM + RL?
1. Long Short-Term Memory (LSTM)
LSTM is a type of recurrent neural network (RNN) that is highly effective at analyzing time-series data and capturing patterns in sequential information. In the context of Quran memorization, LSTM models are especially valuable because they can retain information over multiple time steps, which is crucial for understanding a student’s progress and predicting future performance.

Capturing Sequential Dependencies: Quran memorization is a cumulative process where each recitation session builds upon previous sessions. LSTM models can capture these dependencies by processing sequences of past performance data, such as the number of verses recited, error rates, and pace. This enables the model to learn patterns over time, allowing it to generate a baseline for the student’s daily memorization and revision targets.

Predicting Memorization Trajectories: By analyzing historical data for each student, the LSTM model can identify trends in their learning speed, consistency, and areas of difficulty. For example, if a student has shown steady progress in memorizing around five verses per session with minimal errors, the LSTM can predict that the student may be ready for a slight increase in their daily target. Conversely, if past data shows recurring challenges in specific surahs or verses, the model can adjust the predictions to ensure those areas are reviewed more thoroughly.

Foundation for Baseline Plans: The LSTM generates a personalized baseline plan for each student, setting daily targets for new memorization and revision based on past patterns. This initial plan is adaptive, meaning that it aligns with each student’s unique pace and retention needs. However, to make it more responsive, this baseline is further refined by the RL component to account for real-time performance.

In short, LSTM acts as the “memory” component of the system, enabling the model to set data-driven, personalized targets based on each student’s historical recitation patterns.

2. Reinforcement Learning (RL)
Reinforcement Learning (RL) is an approach where an agent learns by interacting with an environment and receiving feedback in the form of rewards or penalties. In this project, RL enhances the memorization plan by dynamically adjusting the daily targets in response to each student’s performance during their most recent recitation sessions.

Real-Time Plan Adjustments: Unlike LSTM, which generates a baseline plan based on historical data, RL enables real-time adjustments. After each recitation session, the RL model reviews performance metrics, such as the number of errors, skipped revisions, or the student’s accuracy, and then fine-tunes the plan accordingly. For instance, if a student completes a session with high accuracy and few errors, the RL model may slightly increase the new verse target for the next session. Conversely, if the student’s accuracy drops or errors increase, the RL agent may prioritize additional review sessions.

Adaptability to Immediate Performance: RL’s strength lies in its responsiveness to immediate changes in performance. For example, if the student hasn’t revised a certain surah in a long time, the RL agent can add that surah to the daily plan, even if it wasn’t in the LSTM-generated baseline. This ensures that the student doesn’t lose retention for previously memorized sections while working on new material.

Reinforcement Through Rewards and Penalties: The RL model uses a reward system to guide adjustments in the daily plan. Positive performance metrics, like high accuracy and consistency, may lead to rewards in the form of additional verses for new memorization. In contrast, penalties, such as repeated errors, can reduce the daily target or introduce more review sessions. This reward-based feedback mechanism encourages a balance between progress and retention, helping the student advance without feeling overwhelmed.

In essence, RL serves as the “adaptive” component of the system, enabling real-time fine-tuning of the daily plan based on current performance while ensuring the plan remains manageable and effective.
