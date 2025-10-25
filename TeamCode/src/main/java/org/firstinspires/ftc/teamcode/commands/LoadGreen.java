package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.CommandBase;

public class LoadGreen extends CommandBase {
    private final SpindexerSystem spindexer;

    public LoadGreen(final SpindexerSystem spindexer) {
        this.spindexer = spindexer;
        addRequirements(spindexer);
    }

    @Override
    public void initialize() {}

    @Override
    public boolean isFinished() {}

}