#!/bin/bash
cd ..
GRADLE_ARG_LINE=()

for arg in "$@"
do
    case "$arg" in
        *)
            GRADLE_ARG_LINE+=("$arg")
        ;;
    esac
done

startDateTime=`date +%s`

# check that Gradle args are non empty
if [ "$GRADLE_ARG_LINE" != "" ] ; then
    gradleBin="gradle"
    if [ -a $GRADLE_HOME/bin/gradle ] ; then
       gradleBin="$GRADLE_HOME/bin/gradle"
    fi
    echo
    echo "Running Gradle build on available projects (using Gradle binary '$gradleBin')"

    "$gradleBin" -v
    echo
    projects=( "*-model" "*-kjar" "MYSERVICE_NAME_MARKER")

    for suffix in "${projects[@]}"; do

        for repository in $suffix;  do
        echo
            if [ -d "$repository" ]; then
                echo "==============================================================================="
                echo "$repository"
                echo "==============================================================================="

                cd $repository

                "$gradleBin" "init"
                "$gradleBin" "${GRADLE_ARG_LINE[@]}"
                returnCode=$?

                if [ $returnCode != 0 ] ; then
                    exit $returnCode
                fi

                cd ..
                fi

        done;
    done;
    endDateTime=`date +%s`
    spentSeconds=`expr $endDateTime - $startDateTime`
    echo
    echo "Total build time: ${spentSeconds}s"

else
    echo "No Gradle arguments skipping Gradle build"

fi




echo "Launching the application locally..."
pattern="MYSERVICE_NAME_MARKER"
files=( $pattern )
cd ${files[0]}
executable="$(ls  *build/libs/*.jar | tail -n1)"
java -jar "$executable"
